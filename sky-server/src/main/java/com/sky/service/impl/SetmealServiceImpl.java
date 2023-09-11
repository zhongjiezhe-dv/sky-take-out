package com.sky.service.impl;

import com.sky.entity.Setmeal;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetMealDishMapper;
import com.sky.mapper.SetMealMapper;
import com.sky.service.SetmealService;
import com.sky.vo.DishItemVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SetmealServiceImpl
 *
 * @author zyb
 * @version 1.0
 * @description
 * @date 2023/9/11 21:19
 */
@Service
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private SetMealMapper setMealMapper;
    @Autowired
    private SetMealDishMapper setMealDishMapper;
    @Autowired
    private DishMapper dishMapper;
    @Override
    public List<Setmeal> list(Setmeal setmeal) {
        List<Setmeal> list=setMealMapper.list(setmeal);
        return list;
    }

    @Override
    public List<DishItemVO> getDishItemById(Long id) {
       return setMealMapper.getDishItemById(id);

    }
}
