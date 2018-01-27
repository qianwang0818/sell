package com.imooc.domain;

import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "order_master", schema = "sell", catalog = "")
@DynamicUpdate  //针对mysql的自更新时间字段.当其它字段有更新时,会自动更新updateTime的时间
@Data
public class OrderMaster {
    @Id
    @Column(name = "order_id")
    private String orderId;
    @Column(name = "buyer_name")

    private String buyerName;
    @Column(name = "buyer_phone")
    private String buyerPhone;
    @Column(name = "buyer_address")
    private String buyerAddress;
    @Column(name = "buyer_openid")
    private String buyerOpenid;
    @Column(name = "order_amount")

    private BigDecimal orderAmount;
    @Column(name = "order_status")
    private Integer orderStatus = OrderStatusEnum.NEW.getCode() ;   //默认0为新下单
    @Column(name = "pay_status")
    private Integer payStatus = PayStatusEnum.UNPAY.getCode();      //默认未支付,等待支付
    //创建时间和更新时间在排序显示时会有用
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;

}
