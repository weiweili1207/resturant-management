package com.dine.service;

import com.dine.dto.DishDTO;
import com.dine.dto.DishPageQueryDTO;
import com.dine.entity.Dish;
import com.dine.result.PageResult;
import com.dine.vo.DishVO;

import java.util.List;

public interface DishService {
    /**
     * add new dish
     * @param dishDTO
     */
    void addDish(DishDTO dishDTO);

    /**
     * dish pagination
     * @param dishPageQueryDTO
     * @return
     */
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * delete dish
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    /**
     * search dish by id
     * @param id
     * @return
     */
    DishVO getDishById(Long id);

    /**
     * update dish and flavor
     * @param dishDTO
     */
    void updateWithFlavor(DishDTO dishDTO);

    /**
     * get a list of dishes by category id
     * @param categoryId
     * @return
     */
    List<Dish> list(Long categoryId);
}
