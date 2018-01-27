package com.imooc.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.imooc.domain.OrderDetail;
import com.imooc.utils.serializer.DateToLongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {

    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;
    private BigDecimal orderAmount;
    private Integer orderStatus;   //默认0为新下单
    private Integer payStatus;      //默认未支付,等待支付

    @JsonSerialize(using = DateToLongSerializer.class)
    private Date updateTime;
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date createTime;

    private List<OrderDetail> orderDetailList;

}
