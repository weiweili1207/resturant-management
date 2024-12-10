package com.dine.service.impl;

import com.dine.dto.ComboDTO;
import com.dine.entity.Combo;
import com.dine.entity.ComboDish;
import com.dine.mapper.ComboDishMapper;
import com.dine.mapper.ComboMapper;
import com.dine.service.ComboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
public class ComboServiceImpl implements ComboService {
    @Autowired
    private ComboMapper comboMapper;
    @Autowired
    private ComboDishMapper comboDishMapper;
    /**
     * add a new combo and also need to keep combo and dish relationship
     * @param comboDTO
     */
    @Transactional
    public void addCombo(ComboDTO comboDTO) {
        Combo combo = new Combo();
        BeanUtils.copyProperties(comboDTO, combo);
        //add combo to combo table
        comboMapper.insert(combo);
        //get the increment combo id
        Long comboId = combo.getId();
        //set combo and dish relationship
        List<ComboDish> comboDishes = comboDTO.getComboDishes();
        if (comboDishes != null && comboDishes.size() > 0) {
            comboDishes.forEach(comboDish -> {comboDish.setComboId(comboId);});
            comboDishMapper.insertBatch(comboDishes);
        }
    }
}
