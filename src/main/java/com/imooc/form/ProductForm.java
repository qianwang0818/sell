package com.imooc.form;

import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductForm {

    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productStock;

    private String productDescription;
    private String productIcon;
    private Integer categoryType;

}
