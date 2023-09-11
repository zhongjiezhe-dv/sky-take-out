package com.sky.service;

import com.sky.annotation.AutoFill;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.enumeration.OperationType;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * DIshService
 *
 * @author zyb
 * @version 1.0
 * @description
 * @date 2023/8/29 17:11
 */
public interface DishService {
    /**
     *
     * @author zyb
     * @date 2023/8/29 17:15
     * @param dishDTO
     */
    @AutoFill(value = OperationType.INSERT)
    void saveWithFlavor(DishDTO dishDTO);

    /**
     *
     * @author zyb
     * @date 2023/8/30 18:22
     * @param queryDTO
     * @return PageResult
     */
    PageResult pageQuery(DishPageQueryDTO queryDTO);


    /**
     * 批量删除菜品
     * @author zyb
     * @date 2023/8/30 19:47
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    DishVO getById(Long id);

    void updateWithFlavor(DishDTO dishDTO);

    void startOrStop(Integer status, Long id);
}
