package com.dine.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ComboPageQueryDTO implements Serializable {

    private int page;

    private int pageSize;

    private String name;

    //category id
    private Integer categoryId;

    //status 0 disable 1 enable
    private Integer status;

}
