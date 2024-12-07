package com.dine.service.impl;

import com.dine.dto.DishDTO;
import com.dine.entity.Dish;
import com.dine.entity.DishFlavor;
import com.dine.mapper.DishFlavorMapper;
import com.dine.mapper.DishMapper;
import com.dine.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;
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
}
