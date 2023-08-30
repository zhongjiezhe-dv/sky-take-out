package com.sky.mapper;

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
}
