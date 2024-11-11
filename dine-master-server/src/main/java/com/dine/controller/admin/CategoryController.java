package com.dine.controller.admin;

import com.dine.dto.CategoryDTO;
import com.dine.result.Result;
import com.dine.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    
}
