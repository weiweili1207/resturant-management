package com.dine.controller.admin;

import com.dine.dto.CategoryDTO;
import com.dine.dto.CategoryPageQueryDTO;
import com.dine.result.PageResult;
import com.dine.result.Result;
import com.dine.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/category")
@Slf4j
@Api(tags="category-related API")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * add new category
     * @param categoryDTO
     * @return
     */
    @PostMapping
    @ApiOperation("add new category")
    public Result addCategory(@RequestBody CategoryDTO categoryDTO) {
        log.info("add new category: {}", categoryDTO);
        categoryService.addCategory(categoryDTO);
        return Result.success();
    }

    /**
     * category pagination
     * @param categoryPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("category pagination")
    public Result<PageResult> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
        log.info("category pagination, parameter {}", categoryPageQueryDTO);
        PageResult pageResult = categoryService.pageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * delete category by id
     * @param id
     * @return
     */
    @DeleteMapping
    @ApiOperation("delete category")
    public Result<String> deleteCategoryById(Long id)  {
        log.info("delete category: {}", id);
        categoryService.deleteCategoryById(id);
        return Result.success();
    }

    /**
     * update category information
     * @param categoryDTO
     * @return
     */
    @PutMapping
    @ApiOperation("update category information")
    public Result updateCategoryInfo(@RequestBody CategoryDTO categoryDTO) {
        categoryService.updateCategoryInfo(categoryDTO);
        return  Result.success();
    }

    @PostMapping("/status/{status}")
    @ApiOperation("enable or disable category")
    public Result enableOrDisable(@PathVariable("status") Integer status, Long id) {
        categoryService.enableOrDisable(status, id);
        return Result.success();
    }
}
