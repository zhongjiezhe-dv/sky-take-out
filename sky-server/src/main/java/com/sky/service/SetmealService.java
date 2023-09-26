package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;

import java.util.List;

/**
 * SetmealService
 *
 * @author zyb
 * @version 1.0
 * @description
 * @date 2023/9/11 21:19
 */
public interface SetmealService {

    /**
     * 条件查询
     * @author zyb
     * @date 2023/9/11 21:22
     * @param setmeal
     * @return List<Setmeal>
     */
    List<Setmeal> list(Setmeal setmeal);


    List<DishItemVO> getDishItemById(Long id);

    /**
     * 新增套餐，同时需要保存套餐和菜品的关系
     * @author zyb
     * @date 2023/9/14 22:17
     * @param setmealDTO
     */
    void saveWithDish(SetmealDTO setmealDTO);

    /**
     * 分页查询
     * @author zyb
     * @date 2023/9/14 22:25
     * @param setmealPageQueryDTO
     * @return PageResult
     */
    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * 批量删除套餐
     * @author zyb
     * @date 2023/9/14 22:56
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    /**
     * 根据id查询套餐和关联的菜品数据
     * @author zyb
     * @date 2023/9/14 23:05
     * @param id
     * @return SetmealVO
     */
    SetmealVO getByIdWithDish(Long id);

    /** 
     * 修改套餐
     * @author zyb
     * @date 2023/9/14 23:06
     * @param setmealDTO
     */
    void update(SetmealDTO setmealDTO);

    /**
     * 起售或停售套餐
     * @author zyb
     * @date 2023/9/14 23:24
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);
}
