package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.entity.SetmealDish;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * SetMealDishMapper
 *
 * @author zyb
 * @version 1.0
 * @description
 * @date 2023/8/30 21:03
 */
@Mapper
public interface SetMealDishMapper {
    List<Long> getSetMealIdsByDishId(List<Long> ids);

    /**
     * 批量保存套餐和菜品的关联关系
     *
     * @param setmealDishes
     * @author zyb
     * @date 2023/9/14 22:21
     */
    @AutoFill(OperationType.INSERT)
    void insertBatch(List<SetmealDish> setmealDishes);

    @Delete("delete from setmeal_dish where setmeal_id=#{setmealId}")
    void deleteBySetmealId(Long setmealId);

    @Select("select * from setmeal_dish where setmeal_id=#{id}")
    List<SetmealDish> getBySetmealId(Long id);
}
