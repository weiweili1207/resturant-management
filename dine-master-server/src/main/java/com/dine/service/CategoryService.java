package com.dine.service;

import com.dine.dto.CategoryDTO;
import com.dine.dto.CategoryPageQueryDTO;
import com.dine.result.PageResult;

public interface CategoryService {

    /**
     * add new category
     * @param categoryDTO
     */
    void addCategory(CategoryDTO categoryDTO);

    /**
     * category pagination
     * @param categoryPageQueryDTO
     * @return
     */
    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);
}
