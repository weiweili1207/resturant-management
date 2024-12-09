package com.dine.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ComboDishMapper {

    /**
     * get combo id by dish id
     * @param dishIds
     * @return
     */
    //@Select("SELECT set_meal_id FROM set_meal_dish WHERE dish_id IN #{ids}")
    List<Long> getComboIdByDishId(List<Long> dishIds);
}
