package com.dine.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * combo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Combo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    //category id
    private Long categoryId;

    //combo name
    private String name;

    //combo price
    private BigDecimal price;

    //status 0:disable 1:enable
    private Integer status;

    //description
    private String description;

    //image
    private String image;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Long createUser;

    private Long updateUser;
}
