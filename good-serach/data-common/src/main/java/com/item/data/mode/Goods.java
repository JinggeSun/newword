package com.item.data.mode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zcm
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Goods {

    private String name;
    private String img;
    private double price;
    private String shop;

}
