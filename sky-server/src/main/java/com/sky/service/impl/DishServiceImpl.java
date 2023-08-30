package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetMealDishMapper;
import com.sky.mapper.SetMealMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * DishServiceImpl
 *
 * @author zyb
 * @version 1.0
 * @description
 * @date 2023/8/29 17:11
 */

@Service
@Slf4j
public class DishServiceImpl implements DishService {
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;
    @Autowired
    private SetMealMapper setMealMapper;

    @Autowired
    private SetMealDishMapper setMealDishMapper;

    /**
     * 新增菜品和对应的口味
     *
     * @param dishDTO
     * @author zyb
     * @date 2023/8/29 17:15
     */
    @Override
    @Transactional
    public void saveWithFlavor(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);

        dishMapper.insert(dish);

        Long dishId = dish.getId();//获取insert语句生成的主键

        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (flavors != null && flavors.size() > 0) {
            flavors.forEach(dishFlavor -> {
                dishFlavor.setDishId(dishId);
            });
            //口味插入多条数据
            dishFlavorMapper.insertBatch(flavors);
        }
    }

    @Override
    public PageResult pageQuery(DishPageQueryDTO queryDTO) {
        PageHelper.startPage(queryDTO.getPage(), queryDTO.getPageSize());
        Page<DishVO> dishPage = dishMapper.pageQuery(queryDTO);
        return new PageResult(dishPage.getTotal(), dishPage.getResult());
    }

    @Override
    public void deleteBatch(List<Long> ids) {
        /*
           业务要求：
           1.如果菜品在起售的话，不能删除
           2.如果菜品关联着哪个套餐的话，不能删除
         */
        //判断当前菜品是否能够删除，也就是是否在起售中
        for (Long id : ids) {
            Dish dish = dishMapper.getById(id);
            if (dish.getStatus() == StatusConstant.ENABLE) {
                throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
            }
        }


        //判断菜品是否被关联了
        List<Long> setMealIds = setMealDishMapper.getSetMealIdsByDishId(ids);
        if (setMealIds.size()>0&&setMealIds!=null){
            throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_SETMEAL);
        }


        //删除菜品中的数据
        for (Long id : ids) {
            dishMapper.deleteById(id);
            dishFlavorMapper.deleteById(id);
        }
    }
}
