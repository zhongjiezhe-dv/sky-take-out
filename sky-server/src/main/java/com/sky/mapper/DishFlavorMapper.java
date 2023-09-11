package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * DishFlavorMapper
 *
 * @author zyb
 * @version 1.0
 * @description
 * @date 2023/8/29 17:20
 */
@Mapper
public interface DishFlavorMapper {
    /**
     * 批量插入口味
     *
     * @param flavors
     * @author zyb
     * @date 2023/8/29 17:25
     */
    void insertBatch(List<DishFlavor> flavors);

    @Delete("delete from dish_flavor where dish_id =#{dish_id}")
    void deleteById(Long dishId);

    @Select("select * from dish_flavor where dish_id=#{dish_id}")
    List<DishFlavor> getByDishId(Long id);
}
