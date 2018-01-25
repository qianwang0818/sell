package com.imooc.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "product_info", schema = "sell", catalog = "")
@DynamicUpdate  //针对mysql的自更新时间字段.当其它字段有更新时,会自动更新updateTime的时间
@Data
@NoArgsConstructor
public class ProductInfo {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(generator = "mytableGenerator")
    @GenericGenerator(name = "mytableGenerator", strategy = "uuid")
    private String productId;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_price")
    private BigDecimal productPrice;
    @Column(name = "product_stock")
    private Integer productStock;

    @Column(name = "product_description")
    private String productDescription;
    @Column(name = "product_icon")
    private String productIcon;
    @Column(name = "product_status")
    private Integer productStatus;     //正常:0 , 下架1
    @Column(name = "category_type")
    private Integer categoryType;

    public ProductInfo(String productName, BigDecimal productPrice, Integer productStock, String productDescription, String productIcon, Integer productStatus, Integer categoryType) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.productDescription = productDescription;
        this.productIcon = productIcon;
        this.productStatus = productStatus;
        this.categoryType = categoryType;
    }
}
