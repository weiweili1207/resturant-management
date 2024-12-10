package com.dine.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * combo and dish relationship
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComboDish implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    //combo id
    private Long comboId;

    //dish id
    private Long dishId;

    //dish name
    private String name;

    //dish price
    private BigDecimal price;

    //number of copy
    private Integer copies;
}
