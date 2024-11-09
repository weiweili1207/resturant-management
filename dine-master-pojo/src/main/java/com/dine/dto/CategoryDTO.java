package com.dine.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryDTO implements Serializable {

    //primary key
    private Long id;

    //类型 1 菜品分类 2 套餐分类
    private Integer type;

    //category name
    private String name;

    //sort
    private Integer sort;

}
