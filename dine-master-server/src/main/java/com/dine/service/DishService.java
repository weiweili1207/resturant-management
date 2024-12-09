package com.dine.service;

import com.dine.dto.DishDTO;
import com.dine.dto.DishPageQueryDTO;
import com.dine.result.PageResult;

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
}
