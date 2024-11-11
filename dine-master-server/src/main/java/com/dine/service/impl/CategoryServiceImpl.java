package com.dine.service.impl;

import com.dine.constant.StatusConstant;
import com.dine.context.BaseContext;
import com.dine.dto.CategoryDTO;
import com.dine.dto.CategoryPageQueryDTO;
import com.dine.entity.Category;
import com.dine.mapper.CategoryMapper;
import com.dine.result.PageResult;
import com.dine.service.CategoryService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    /**
     * add new category
     * @param categoryDTO
     */
    @Override
    public void addCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        //copy from categoryDTO to category
        BeanUtils.copyProperties(categoryDTO, category);
        category.setStatus(StatusConstant.DISABLE);
        //set create time and update time
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        //set create user and update user
        category.setCreateUser(BaseContext.getCurrentId());
        category.setUpdateUser(BaseContext.getCurrentId());
        categoryMapper.addCategory(category);
    }

    /**
     * category pagination
     * @param categoryPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
        //categoryPageQueryDTO.getPage(): This gets the current page number from the categoryPageQueryDTO object
        //categoryPageQueryDTO.getPageSize(): This gets the number of records to display per page from categoryPageQueryDTO
        PageHelper.startPage(categoryPageQueryDTO.getPage(), categoryPageQueryDTO.getPageSize());
        Page<Category> page = categoryMapper.pageQuery(categoryPageQueryDTO);

        long totalPage = page.getTotal();
        List<Category> categories = page.getResult();
        return new PageResult(totalPage, categories);
    }
}
