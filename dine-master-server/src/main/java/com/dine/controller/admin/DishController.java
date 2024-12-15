package com.dine.controller.admin;

import com.dine.dto.DishDTO;
import com.dine.dto.DishPageQueryDTO;
import com.dine.entity.Dish;
import com.dine.result.PageResult;
import com.dine.service.DishService;
import com.dine.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.dine.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        log.info("starting to add new dish: {}", dishDTO);
        dishService.addDish(dishDTO);
        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation("dish pagination")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO){
        log.info("dish page query：{}",dishPageQueryDTO);
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    @DeleteMapping
    @ApiOperation("delete a batch of dish")
    public Result delete(@RequestParam List<Long> ids){
        log.info("delete a batch of dish：{}",ids);
        dishService.deleteBatch(ids);
        return Result.success();
    }

    @GetMapping("/{id}")
    @ApiOperation("get dish by id")
    public Result<DishVO> getDishById(@PathVariable Long id) {
        log.info("get dish by id: {}", id);
        DishVO dishVO = dishService.getDishById(id);
        return Result.success(dishVO);
    }

    @PutMapping
    @ApiOperation("update dish")
    public Result update(@RequestBody DishDTO dishDTO) {
        log.info("Starting to update dish: {}", dishDTO);
        dishService.updateWithFlavor(dishDTO);
        return Result.success();
    }

    @GetMapping("/list")
    @ApiOperation("get dish by category id")
    public Result<List<Dish>> list(Long categoryId) {
        log.info("get dish by category id: {}", categoryId);
        List<Dish> dishList = dishService.list(categoryId);
        return Result.success(dishList);
    }

    @PostMapping("/status/{status}")
    @ApiOperation("enable or disable dish")
    public Result enableOrDisable(@PathVariable Integer status, Long id) {
        dishService.enableOrDisable(status, id);
        return Result.success();
    }
}
