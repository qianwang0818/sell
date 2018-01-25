package com.imooc.DTO;

import com.imooc.domain.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Data
public class OrderDTO {

    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;
    private BigDecimal orderAmount;
    private Integer orderStatus;   //默认0为新下单
    private Integer payStatus;      //默认未支付,等待支付
    private Timestamp createTime;
    private Timestamp updateTime;

    private List<OrderDetail> orderDetailList;

}
