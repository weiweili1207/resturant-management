package com.dine.service;

import com.dine.dto.ComboDTO;
import com.dine.dto.ComboPageQueryDTO;
import com.dine.result.PageResult;
import com.dine.vo.ComboVO;

import java.util.List;

public interface ComboService {
    /**
     * add new combo
     * @param comboDTO
     */
    void addCombo(ComboDTO comboDTO);

    /**
     * combo pagination
     * @param comboPageQueryDTO
     * @return
     */
    PageResult pageQuery(ComboPageQueryDTO comboPageQueryDTO);

    /**
     * delete combo
     * @param ids
     */
    void delete(List<Long> ids);

    /**
     * get combo by id
     * @param id
     * @return
     */
    ComboVO getComboById(Long id);

    /**
     * update combo
     * @param comboDTO
     */
    void updateCombo(ComboDTO comboDTO);
}
