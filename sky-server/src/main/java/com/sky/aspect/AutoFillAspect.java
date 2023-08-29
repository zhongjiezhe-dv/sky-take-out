package com.sky.aspect;

import com.sky.annotation.AutoFill;
import com.sky.constant.AutoFillConstant;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * AutoFillAspect
 *
 * @author zyb
 * @version 1.0
 * @description
 * @date 2023/8/29 16:11
 */
@Aspect
@Component
@Slf4j
public class AutoFillAspect {
    //切入点
    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill)")
    public void autoFillPointCut(){}

    /*
    前置通知，在通知中进行公告字段的赋值
     */
    @Before("autoFillPointCut()")
    public void addFill(JoinPoint joinPoint){
        //可先进行调试，是否能进入该方法 提前在mapper方法添加AutoFill注解
        log.info("开始公共字段自动填充....");

        //获取到当前被拦截的方法上的数据库操作类型
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();//方法签名对象
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);//获取方法上的注解
        //获取数据库操作类型
        OperationType operationType = autoFill.value();


        //获取到当前被拦截的方法的参数--实体对象
        Object[] args = joinPoint.getArgs();
        if (args==null||args.length==0){
            return;
        }
        Object entity = args[0];

        //准备赋值的数据
        LocalDateTime now = LocalDateTime.now();
        Long currentId = BaseContext.getCurrentId();

        //根据不同类型的操作，为对应的属性通过反射来赋值
        if (operationType==OperationType.INSERT){
            //为4个属性赋值
            try {
                Method createTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME,
                        LocalDateTime.class);
                Method createUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER,
                        Long.class);
                Method updateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME,
                        LocalDateTime.class);
                Method updateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER,
                        Long.class);

                //通过反射为对象赋值
                createTime.invoke(entity,now);
                createUser.invoke(entity,currentId);
                updateTime.invoke(entity,now);
                updateUser.invoke(entity,currentId);

            }catch (Exception e){
                e.printStackTrace();
            }

        }else if(operationType==OperationType.UPDATE){
            //为2个属性赋值
            try {
                Method updateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME,
                        LocalDateTime.class);
                Method updateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);

                updateTime.invoke(entity,now);
                updateUser.invoke(entity,currentId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }
}
