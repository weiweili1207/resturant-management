package com.dine.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    // Type: 1 - Dish Category, 2 - Combo Category
    private Integer type;

    //category name
    private String name;

    //sort
    private Integer sort;

    // Category Status: 0 indicates disabled, 1 indicates enabled
    private Integer status;

    //create time
    private LocalDateTime createTime;

    //update time
    private LocalDateTime updateTime;

    //create user
    private Long createUser;

    //update user
    private Long updateUser;
}
