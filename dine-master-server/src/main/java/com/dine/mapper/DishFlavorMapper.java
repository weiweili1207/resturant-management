package com.dine.mapper;

import com.dine.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorMapper {

    /**
     * insert a batch of flavor into flavor table
     * @param dishFlavors
     */
    void insertBatch(List<DishFlavor> dishFlavors);
}
