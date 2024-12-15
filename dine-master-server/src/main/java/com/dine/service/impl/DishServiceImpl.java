package com.dine.service.impl;

import com.dine.constant.MessageConstant;
import com.dine.constant.StatusConstant;
import com.dine.dto.DishDTO;
import com.dine.dto.DishPageQueryDTO;
import com.dine.entity.Dish;
import com.dine.entity.DishFlavor;
import com.dine.exception.DeletionNotAllowedException;
import com.dine.mapper.ComboDishMapper;
import com.dine.mapper.DishFlavorMapper;
import com.dine.mapper.DishMapper;
import com.dine.result.PageResult;
import com.dine.service.DishService;
import com.dine.vo.DishVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class DishServiceImpl implements DishService {
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;
    @Autowired
    private ComboDishMapper comboDishMapper;
    /**
     * add new dish and flavor
     * @param dishDTO
     */
    @Transactional
    public void addDish(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        //add dish to dish table
        dishMapper.insertDish(dish);
        //get the increment dish id
        Long dishId = dish.getId();
        //add flavor to flavor table
        List<DishFlavor> dishFlavors =  dishDTO.getFlavors();
        if (dishFlavors != null && dishFlavors.size() > 0)
        {
            dishFlavors.forEach(dishFlavor -> {dishFlavor.setDishId(dishId);});
            dishFlavorMapper.insertBatch(dishFlavors);
        }
    }

    /**
     * dish pagination
     * @param dishPageQueryDTO
     * @return
     */
    public PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());
        Page<DishVO> page = dishMapper.pageQuery(dishPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * delete dish
     * @param ids
     */
    @Transactional
    public void deleteBatch(List<Long> ids) {
        //can we delete current dish or not?
        //1. can't delete dish which status is start sell
        for (Long id : ids)
        {
            Dish dish = dishMapper.getDishById(id);
            if (Objects.equals(dish.getStatus(), StatusConstant.ENABLE))
            {
                throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
            }
        }
        //2. can't delete dish which exist in a combo
        List<Long> comboIds = comboDishMapper.getComboIdByDishId(ids);
        if (comboIds != null && comboIds.size() > 0) {
            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_COMBO);
        }

        for (Long id : ids) {
            //3. delete dish
            dishMapper.deleteById(id);
            //4. delete dish flavor
            dishFlavorMapper.deleteByDishId(id);
        }
    }

    /**
     * get dish by id
     * @param id
     * @return
     */
    public DishVO getDishById(Long id) {
        //search dish by id
        Dish dish = dishMapper.getDishById(id);
        //search flavor by dish id
        List<DishFlavor> dishFlavors = dishFlavorMapper.getFlavorByDishId(id);
        //encapsulate into a dishVO
        DishVO dishVO = new DishVO();
        BeanUtils.copyProperties(dish, dishVO);
        dishVO.setFlavors(dishFlavors);
        return dishVO;
    }

    /**
     * update dish and its flavor
     * @param dishDTO
     */
    public void updateWithFlavor(DishDTO dishDTO) {
        Dish dish = new Dish();
        //update dish table basic info
        BeanUtils.copyProperties(dishDTO, dish);
        dishMapper.update(dish);
        //delete original flavor
        dishFlavorMapper.deleteByDishId(dishDTO.getId());
        //add new flavor
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (flavors != null && flavors.size() > 0) {
            flavors.forEach(dishFlavor -> {dishFlavor.setDishId(dishDTO.getId());});
            dishFlavorMapper.insertBatch(flavors);
        }
    }

    /**
     * get list of dishes by category id
     * @param categoryId
     * @return
     */
    public List<Dish> list(Long categoryId) {
        Dish dish = Dish.builder()
                .categoryId(categoryId)
                .status(StatusConstant.ENABLE)
                .build();
        return dishMapper.list(dish);
    }

    /**
     * enable or disable dish
     * @param status
     * @param id
     */
    public void enableOrDisable(Integer status, Long id) {
        Dish dish = Dish.builder()
                .status(status)
                .id(id)
                .build();
        dishMapper.update(dish);
    }
}
