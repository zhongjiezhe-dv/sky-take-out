package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CategoryController
 *
 * @author zyb
 * @version 1.0
 * @description
 * @date 2023/8/28 17:54
 */
@RestController
@RequestMapping("/admin/category")
@Api(tags = "分类管理相关接口")
@Slf4j
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 新增分类信息
     * @author zyb
     * @date 2023/8/28 18:06
     * @param categoryDTO
     * @return Result
     */
    @PostMapping
    @ApiOperation("新增分类")
    public Result save(@RequestBody CategoryDTO categoryDTO){
        log.info("新增分类信息：{}",categoryDTO);
        categoryService.save(categoryDTO);
        return Result.success();
    }


    /**
     * 分页查询
     * @author zyb
     * @date 2023/8/28 18:22
     * @param categoryPageQueryDTO
     * @return Result<PageResult>
     */
    @GetMapping("/page")
    @ApiOperation("分页查询")
    public Result<PageResult> page(CategoryPageQueryDTO categoryPageQueryDTO){
        log.info("分页查询：{}",categoryPageQueryDTO);
        PageResult pageResult=categoryService.pageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);
    }


    /**
     *
     * @author zyb
     * @date 2023/8/28 18:34
     * @param id
     * @return Result<String>
     */
    @DeleteMapping
    @ApiOperation("删除分类")
    public Result<String> deleteById(Long id){
        log.info("删除分类：{}",id);
        categoryService.deleteById(id);
        return Result.success();
    }

    /**
     *
     * @author zyb
     * @date 2023/8/28 22:14
     * @param status
     * @param id
     * @return Result
     */
    @PostMapping("/status/{status}")
    @ApiOperation("启用禁用分类")
    public Result startOrStop(@PathVariable Integer status,Long id){
        categoryService.startOrStop(status,id);
        return Result.success();
    }

    /**
     *
     * @author zyb
     * @date 2023/8/28 22:14
     * @param categoryDTO
     * @return Result<String>
     */

    @PutMapping
    @ApiOperation("编辑分类")
    public Result<String> update (@RequestBody CategoryDTO categoryDTO){
        categoryService.update(categoryDTO);
        return Result.success();
    }

    @GetMapping("/list")
    @ApiOperation("根据类型查询分类")
    public Result<List<Category>> list(Integer type,String name){
        List<Category> categoryList= categoryService.list(type,name);
        return Result.success(categoryList);
    }
}
