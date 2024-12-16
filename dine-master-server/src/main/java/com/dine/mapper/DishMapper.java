package com.dine.mapper;

import com.dine.annotation.AutoFill;
import com.dine.dto.DishPageQueryDTO;
import com.dine.entity.Dish;
import com.dine.enumeration.OperationType;
import com.dine.vo.DishVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    /**
     * get dish by id
     * @param id
     * @return
     */
    @Select("SELECT * FROM dish WHERE id = #{id}")
    Dish getDishById(Long id);

    /**
     * delete a dish
     * @param id
     */
    @Delete("DELETE FROM dish WHERE id = #{id}")
    void deleteById(Long id);

    /**
     * update a dish
     * @param dish
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Dish dish);

    /**
     * get list of dishes
     * @param dish
     * @return
     */
    List<Dish> list(Dish dish);

    /**
     * get dish list by combo id
     * @param comboId
     * @return
     */
    @Select("SELECT d.* FROM dish d LEFT JOIN set_meal_dish s ON d.id = s.dish_id WHERE s.set_meal_id = ${comboId}")
    List<Dish> getDishByComboId(Long comboId);
}
