package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

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

    void insert(Dish dish);

    Page<DishVO> pageQuery(DishPageQueryDTO queryDTO);

    @Delete("delete from dish where id=#{id}")
    void deleteById(Long id);

    @Select("select * from dish where id=#{id}")
    Dish getById(Long id);

    @AutoFill(value = OperationType.UPDATE)
    void update(Dish dish);


    List<Dish> list(Dish dish);

    @Select("select d.* from dish d,setmeal_dish sd where d.id=sd.dish_id and sd.setmeal_id=#{id}")
    List<Dish> getSetmeal(Long id);
}
