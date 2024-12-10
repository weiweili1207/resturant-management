package com.dine.dto;

import com.dine.entity.ComboDish;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class ComboDTO implements Serializable {

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

    //combo and dish relation
    private List<ComboDish> comboDishes = new ArrayList<>();

}
