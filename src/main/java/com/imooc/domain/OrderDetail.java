package com.imooc.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_detail", schema = "sell", catalog = "")
@DynamicUpdate  //针对mysql的自更新时间字段.当其它字段有更新时,会自动更新updateTime的时间
@Data
public class OrderDetail {
    @Id
    @Column(name = "detail_id")
    @GeneratedValue(generator = "mytableGenerator")
    @GenericGenerator(name = "mytableGenerator", strategy = "uuid")
    private String detailId;

    @Column(name = "order_id")
    private String orderId;
    @Column(name = "product_id")
    private String productId;
    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_price")
    private BigDecimal productPrice;
    @Column(name = "product_quantity")
    private Integer productQuantity;
    @Column(name = "product_icon")
    private String productIcon;

}
