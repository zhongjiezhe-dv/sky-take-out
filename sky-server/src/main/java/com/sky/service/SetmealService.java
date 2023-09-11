package com.sky.service;

import com.sky.entity.Setmeal;
import com.sky.vo.DishItemVO;

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
}
