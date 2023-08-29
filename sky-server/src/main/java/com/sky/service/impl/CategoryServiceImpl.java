package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.context.BaseContext;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.CategoryMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetMealMapper;
import com.sky.result.PageResult;
import com.sky.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * CategoryServiceImpl
 *
 * @author zyb
 * @version 1.0
 * @description
 * @date 2023/8/28 17:57
 */

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private SetMealMapper setMealMapper;

    /**
     * 新增分类信息
     *
     * @param categoryDTO
     * @author zyb
     * @date 2023/8/28 18:07
     */
    @Override
    public void save(CategoryDTO categoryDTO) {
        Category category = new Category();
        //属性拷贝
        BeanUtils.copyProperties(categoryDTO, category);

        //分类默认状态为0
        category.setStatus(StatusConstant.DISABLE);

        //设置创建时间、修改时间、创建人、修改人
        // category.setCreateTime(LocalDateTime.now());
        // category.setUpdateTime(LocalDateTime.now());
        // category.setCreateUser(BaseContext.getCurrentId());
        // category.setUpdateUser(BaseContext.getCurrentId());

        categoryMapper.insert(category);
    }

    /**
     * 分页查询
     *
     * @param categoryPageQueryDTO
     * @return PageResult
     * @author zyb
     * @date 2023/8/28 18:22
     */
    @Override
    public PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
        PageHelper.startPage(categoryPageQueryDTO.getPage(), categoryPageQueryDTO.getPageSize());
        //下一条sql进行分页，自动加入limit关键字分页
        Page<Category> page = categoryMapper.pageQuery(categoryPageQueryDTO);
        return new PageResult(page.getTotal(),page.getResult());
    }

    /**
     * 删除分类
     * @author zyb
     * @date 2023/8/28 18:35
     * @param id
     */
    @Override
    public void deleteById(Long id) {
       Integer count= dishMapper.countByCategoryId(id);
        if (count > 0) {
            //当前分类中有菜品，不能删除
            throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_DISH);
        }
        count=setMealMapper.countByCategoryId(id);
        if (count>0) {
            //当前分类中有套餐，不能删除
            throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_SETMEAL);
        }
        categoryMapper.deletById(id);

    }

    /**
     * 启用禁用分类
     * @author zyb
     * @date 2023/8/28 22:09
     * @param status
     * @param id
     */
    @Override
    public void startOrStop(Integer status, Long id) {
        Category category = Category.builder()
                .status(status)
                .id(id)
                .updateTime(LocalDateTime.now())
                .updateUser(BaseContext.getCurrentId())
                .build();
        categoryMapper.update(category);
    }

    /**
     * 编辑分类
     * @author zyb
     * @date 2023/8/28 22:38
     * @param categoryDTO
     */
    @Override
    public void update(CategoryDTO categoryDTO) {
        Category category = new Category();
        //属性拷贝
        BeanUtils.copyProperties(categoryDTO,category);

        //设置修改时间、修改人
        // category.setUpdateTime(LocalDateTime.now());
        // category.setUpdateUser(BaseContext.getCurrentId());
        categoryMapper.update(category);
    }

    /**
     * 根据类型查询分类
     * @author zyb
     * @date 2023/8/28 22:38
     * @param type
     * @return List<Category>
     */
    @Override
    public List<Category> list(Integer type){
        return categoryMapper.list(type);
    }
}
