package com.dine.controller.admin;

import com.dine.dto.DishDTO;
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
        log.info("starting to add new dish: {}", dishDTO);
        dishService.addDish(dishDTO);
        return Result.success();
    }
    
}
