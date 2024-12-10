package com.dine.vo;

import com.dine.entity.ComboDish;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComboVO implements Serializable {

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

    //update time
    private LocalDateTime updateTime;

    //category name
    private String categoryName;

    //combo and dish relation
    private List<ComboDish> comboDishes = new ArrayList<>();
}
