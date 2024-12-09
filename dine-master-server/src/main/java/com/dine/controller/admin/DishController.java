package com.dine.controller.admin;

import com.dine.dto.DishDTO;
import com.dine.dto.DishPageQueryDTO;
import com.dine.result.PageResult;
import com.dine.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.dine.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * dish management
 */
@RestController
@RequestMapping("/admin/dish")
@Api(tags = "Dish management related API")
@Slf4j
public class DishController {
    @Autowired
    private DishService dishService;
    /**
     * add new dish
     * @param dishDTO
     * @return
     */
    @PostMapping
    @ApiOperation("add new dish")
    public Result addDish(@RequestBody DishDTO dishDTO) {
        log.info("Starting to add new dish: {}", dishDTO);
        dishService.addDish(dishDTO);
        return Result.success();
    }

    /**
     * dish pagination
     * @param dishPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("dish pagination")
    public Result<PageResult> pageQuery(DishPageQueryDTO dishPageQueryDTO) {
        log.info("Dish pagination, patameter: {} ", dishPageQueryDTO);
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }
}
