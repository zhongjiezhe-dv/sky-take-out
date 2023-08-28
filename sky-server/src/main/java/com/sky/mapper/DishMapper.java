package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * DishMapper
 *
 * @author zyb
 * @version 1.0
 * @description
 * @date 2023/8/28 17:55
 */

@Mapper
public interface DishMapper {
    @Select("select count(id) from dish where category_id=#{category_id}")
    Integer countByCategoryId(Long id);
}
