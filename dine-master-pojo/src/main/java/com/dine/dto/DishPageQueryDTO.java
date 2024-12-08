package com.dine.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DishPageQueryDTO implements Serializable {

    private int page;

    private int pageSize;

    private String name;

    //category id
    private Integer categoryId;

    //status 0表示禁用 1表示启用
    private Integer status;

}
