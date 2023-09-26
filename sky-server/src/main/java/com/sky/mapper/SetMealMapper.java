package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;
import org.apache.ibatis.annotations.Delete;
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

    /**
     * 通过套餐id查询菜品列表
     *
     * @param id
     * @return List<DishItemVO>
     * @author zyb
     * @date 2023/9/14 22:27
     */
    @Select("select sd.name, sd.copies, d.image, d.description " +
            "from setmeal_dish sd left join dish d on sd.dish_id = d.id " +
            "where sd.setmeal_id = #{id}")
    List<DishItemVO> getDishItemById(Long id);

    /**
     * 新增套餐
     *
     * @param setmeal
     * @author zyb
     * @date 2023/9/14 22:27
     */
    @AutoFill(value = OperationType.INSERT)
    void insert(Setmeal setmeal);

    /**
     * 分页查询
     *
     * @param setmealPageQueryDTO
     * @return Page<SetmealVO>
     * @author zyb
     * @date 2023/9/14 22:27
     */
    Page<SetmealVO> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    @Select("select * from setmeal where id=#{id}")
    Setmeal getById(Long id);


    @Delete("delete from setmeal where id=#{setmealId}")
    void deleteById(Long setmealId);

    @AutoFill(OperationType.UPDATE)
    void update(Setmeal setmeal);
}
