package com.dine.service.impl;

import com.dine.constant.MessageConstant;
import com.dine.constant.StatusConstant;
import com.dine.dto.ComboDTO;
import com.dine.dto.ComboPageQueryDTO;
import com.dine.entity.Combo;
import com.dine.entity.ComboDish;
import com.dine.entity.Dish;
import com.dine.exception.DeletionNotAllowedException;
import com.dine.exception.SetmealEnableFailedException;
import com.dine.mapper.ComboDishMapper;
import com.dine.mapper.ComboMapper;
import com.dine.mapper.DishMapper;
import com.dine.result.PageResult;
import com.dine.service.ComboService;
import com.dine.vo.ComboVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
    @Autowired
    private DishMapper dishMapper;
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

    /**
     * combo pagination
     * @param comboPageQueryDTO
     * @return
     */
    public PageResult pageQuery(ComboPageQueryDTO comboPageQueryDTO) {
        PageHelper.startPage(comboPageQueryDTO.getPage(), comboPageQueryDTO.getPageSize());
        Page<ComboVO> page = comboMapper.pageQuery(comboPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * delete combo
     * @param ids
     */
    @Transactional
    public void delete(List<Long> ids) {
        //can we delete current combo? if it's status is enabled we can't
        ids.forEach(id -> {
            Combo combo = comboMapper.getComboById(id);
            if (combo.getStatus().equals(StatusConstant.ENABLE)) {
                throw new DeletionNotAllowedException(MessageConstant.COMBO_ON_SALE);
            }
        });

        ids.forEach(id -> {
            //delete combo
            comboMapper.deleteComboById(id);
            //delete combo-dish relation
            comboDishMapper.deleteByComboId(id);
        });
    }

    /**
     * get combo by id
     * @param id
     * @return
     */
    public ComboVO getComboById(Long id) {
        //search combo by id
        Combo combo = comboMapper.getComboById(id);
        //get combo and dish relation
        List<ComboDish> comboDishes = comboDishMapper.getByComboId(id);
        //encapsulate into a vo
        ComboVO comboVO = new ComboVO();
        BeanUtils.copyProperties(combo, comboVO);
        comboVO.setComboDishes(comboDishes);
        return comboVO;
    }

    /**
     * update combo
     * @param comboDTO
     */
    public void updateCombo(ComboDTO comboDTO) {
        //encapsulate the comboDTO as a Combo
        Combo combo = new Combo();
        BeanUtils.copyProperties(comboDTO, combo);
        comboMapper.update(combo);
        //delete combo and dish relationship
        Long comboId = comboDTO.getId();
        comboDishMapper.deleteByComboId(comboId);
        List<ComboDish> comboDishes = comboDTO.getComboDishes();
        if (comboDishes != null && comboDishes.size() > 0) {
            comboDishes.forEach(comboDish -> {comboDish.setComboId(comboId);});
            //insert the new combo & dish relationship
            comboDishMapper.insertBatch(comboDishes);
        }
    }

    /**
     * enable or disable combo
     * @param status
     * @param id
     */
    public void enableOrDisable(Integer status, Long id) {
        //if there exists any disable dish in the combo is disabled we can't enable the combo
        if (status.equals(StatusConstant.ENABLE)) {
            //get the list of dishes by combo id
            List<Dish> dishList = dishMapper.getDishByComboId(id);
            dishList.forEach(dish -> {
                if (dish.getStatus().equals(StatusConstant.DISABLE)) {
                    throw new SetmealEnableFailedException(MessageConstant.COMBO_ENABLE_FAILED);
                }
            });
        }
        Combo combo = Combo.builder()
                .status(status)
                .id(id)
                .build();
        comboMapper.update(combo);
    }
}
