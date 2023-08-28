package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * SetmealMapper
 *
 * @author zyb
 * @version 1.0
 * @description
 * @date 2023/8/28 17:55
 */

@Mapper
public interface SetMealMapper {
    @Select("select count(id) from setmeal where category_id=#{category_id}")
    Integer countByCategoryId(Long id);
}
