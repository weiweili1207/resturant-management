package com.dine.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeePageQueryDTO implements Serializable {

    //employee name
    private String name;

    //page number
    private int page;

    //record count per page
    private int pageSize;

}
