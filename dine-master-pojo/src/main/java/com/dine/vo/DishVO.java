package com.dine.vo;

import com.dine.entity.DishFlavor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DishVO implements Serializable {

    private Long id;
    //dish name
    private String name;
    //category id
    private Long categoryId;
    //dish price
    private BigDecimal price;
    //image
    private String image;
    //description info
    private String description;
    //0 stop sell 1 start sell
    private Integer status;
    //update time
    private LocalDateTime updateTime;
    //category name
    private String categoryName;
    //dish related flavor
    private List<DishFlavor> flavors = new ArrayList<>();

    //private Integer copies;
}
