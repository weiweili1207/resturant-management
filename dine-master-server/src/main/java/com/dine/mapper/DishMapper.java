package com.dine.mapper;

import com.dine.annotation.AutoFill;
import com.dine.dto.DishPageQueryDTO;
import com.dine.entity.Dish;
import com.dine.enumeration.OperationType;
import com.dine.vo.DishVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DishMapper {

    @Select("SELECT COUNT(id) FROM dish WHERE category_id = #{categoryId}")
    Integer countDishByCategoryId(Long categoryId);

    /**
     * insert dish
     * @param dish
     */
    @AutoFill(value = OperationType.INSERT)
    void insertDish(Dish dish);

    /**
     * dish pagination
     * @param dishPageQueryDTO
     * @return
     */
    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);
}
