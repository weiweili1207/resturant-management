package com.dine.dto;

import com.dine.entity.DishFlavor;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class DishDTO implements Serializable {

    private Long id;
    //Dish Type
    private String name;
    //category id
    private Long categoryId;
    //dish price
    private BigDecimal price;
    //picture
    private String image;
    //description info
    private String description;
    //0 stop sell 1 start sell
    private Integer status;
    //flavor
    private List<DishFlavor> flavors = new ArrayList<>();

}
