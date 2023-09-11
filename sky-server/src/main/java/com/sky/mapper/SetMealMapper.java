package com.sky.mapper;

import com.sky.entity.Setmeal;
import com.sky.vo.DishItemVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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


    List<Setmeal> list(Setmeal setmeal);

    @Select("select sd.name, sd.copies, d.image, d.description " +
            "from setmeal_dish sd left join dish d on sd.dish_id = d.id " +
            "where sd.setmeal_id = #{id}")
    List<DishItemVO> getDishItemById(Long id);
}
