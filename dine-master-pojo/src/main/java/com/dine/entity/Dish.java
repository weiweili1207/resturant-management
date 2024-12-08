package com.dine.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 菜品
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dish implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    //dish name
    private String name;

    //category id
    private Long categoryId;

    //dish price
    private BigDecimal price;

    //dish image
    private String image;

    //description
    private String description;

    //0 stop sell 1 start sell
    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Long createUser;

    private Long updateUser;

}
