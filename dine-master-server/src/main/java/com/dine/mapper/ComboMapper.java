package com.dine.mapper;

import com.dine.annotation.AutoFill;
import com.dine.dto.ComboPageQueryDTO;
import com.dine.entity.Combo;
import com.dine.enumeration.OperationType;
import com.dine.vo.ComboVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
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

    /**
     * combo pagination
     * @param comboPageQueryDTO
     * @return
     */
    Page<ComboVO> pageQuery(ComboPageQueryDTO comboPageQueryDTO);

    /**
     * get combo by id
     * @param id
     * @return
     */
    @Select("SELECT * FROM set_meal WHERE id = #{id}")
    Combo getComboById(Long id);

    /**
     * delete combo by id
     * @param id
     */
    @Delete("DELETE FROM set_meal WHERE id = #{id}")
    void deleteComboById(Long id);
}
