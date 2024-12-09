package com.dine.service;

import com.dine.dto.DishDTO;
import com.dine.dto.DishPageQueryDTO;
import com.dine.result.PageResult;

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
     * enable or disable dish
     * @param status
     * @param id
     */
    void enableOrDisable(Integer status, Long id);
}
