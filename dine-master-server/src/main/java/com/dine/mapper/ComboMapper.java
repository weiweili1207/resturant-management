package com.dine.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ComboMapper {

    @Select("SELECT COUNT(id) FROM set_meal WHERE category_id = #{categoryId}")
    Integer countComboByCategoryId(Long categoryId);
}
