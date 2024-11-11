package com.dine.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryPageQueryDTO implements Serializable {

    //page number
    private int page;

    //records per page
    private int pageSize;

    //category name
    private String name;

    //type: 1. dish type 2. combo type
    private Integer type;

}
