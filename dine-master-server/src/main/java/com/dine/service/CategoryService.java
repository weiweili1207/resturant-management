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

    /**
     * delete category by id
     * @param id
     */
    void deleteCategoryById(Long id);

    /**
     * update category info
     * @param categoryDTO
     */
    void updateCategoryInfo(CategoryDTO categoryDTO);

    /**
     * enable or disable category
     * @param status
     * @param id
     */
    void enableOrDisable(Integer status, Long id);
}
