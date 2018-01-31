package com.imooc.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 购物车
 * Created by 廖师兄
 * 2017-06-11 19:37
 */
@Data
@AllArgsConstructor
public class CartDTO {

    /** 商品Id. */
    private String productId;

    /** 数量. */
    private Integer productQuantity;

}
