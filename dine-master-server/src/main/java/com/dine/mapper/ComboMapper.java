package com.dine.mapper;

import com.dine.annotation.AutoFill;
import com.dine.entity.Combo;
import com.dine.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ComboMapper {

    @Select("SELECT COUNT(id) FROM set_meal WHERE category_id = #{categoryId}")
    Integer countComboByCategoryId(Long categoryId);

    /**
     * insert combo to table
     * @param combo
     */
    @AutoFill(value = OperationType.INSERT)
    void insert(Combo combo);
}
