package com.dine.service;

import com.dine.dto.DishDTO;

public interface DishService {
    /**
     * add new dish
     * @param dishDTO
     */
    void addDish(DishDTO dishDTO);
}
