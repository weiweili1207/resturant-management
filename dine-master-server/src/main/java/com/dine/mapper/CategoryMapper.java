package com.dine.mapper;

import com.dine.dto.CategoryPageQueryDTO;
import com.dine.entity.Category;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    /**
     * add new category
     * @param category
     */
    @Insert("INSERT INTO category (type, name, sort, status, create_time, update_time, create_user, update_user)"
            + "VALUES " +
            "(#{type}, #{name}, #{sort}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{createUser})")
    void addCategory(Category category);

    /**
     * category pagination
     * @param categoryPageQueryDTO
     * @return
     */
    Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * delete category by id
     * @param id
     */
    @Delete("DELETE FROM category WHERE id = #{id}")
    void deleteCategoryById(Long id);

    /**
     * update category information
     * @param category
     */
    void update(Category category);

    /**
     * get category by type
     * @return
     */
    List<Category> getCategoryByType(Integer type);
}
