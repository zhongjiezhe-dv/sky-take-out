package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CategoryService
 *
 * @author zyb
 * @version 1.0
 * @description
 * @date 2023/8/28 17:56
 */
public interface CategoryService {
    /**
     *
     * @author zyb
     * @date 2023/8/28 18:06
     * @param categoryDTO
     */
    void save(CategoryDTO categoryDTO);

    /**
     *
     * @author zyb
     * @date 2023/8/28 22:07
     * @param categoryPageQueryDTO
     * @return PageResult
     */
    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);
    /**
     *
     * @author zyb
     * @date 2023/8/28 22:07
     * @param id
     */
    void deleteById(Long id);

    /**
     *
     * @author zyb
     * @date 2023/8/28 22:07
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);
    /**
     *
     * @author zyb
     * @date 2023/8/28 22:14
     * @param categoryDTO
     */
    void update(CategoryDTO categoryDTO);

    /**
     * 根据类型查询分类
     * @author zyb
     * @date 2023/8/28 22:37
     * @param type
     * @return List<Category>
     */
    List<Category> list(Integer type);
}
