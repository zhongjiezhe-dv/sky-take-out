package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * CategoryMapper
 *
 * @author zyb
 * @version 1.0
 * @description
 * @date 2023/8/28 17:54
 */

@Mapper
public interface CategoryMapper {
    /**
     *
     * @author zyb
     * @date 2023/8/28 18:16
     * @param category
     */
    @Insert("insert into category(type, name, sort, status, create_time, update_time, create_user, update_user)" +
            " VALUES" +
            " (#{type}, #{name}, #{sort}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    void insert(Category category);

    /**
     * 分页查询
     * @author zyb
     * @date 2023/8/28 18:26
     * @param categoryPageQueryDTO
     * @return Page<Category>
     */
    Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    @Delete("delete from category where id=#{id}")
    void deletById(Long id);

    void update(Category category);

    List<Category> list(Integer type,String name);
}
