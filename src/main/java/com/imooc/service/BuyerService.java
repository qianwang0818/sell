package com.imooc.service;

import com.imooc.DTO.OrderDTO;

import java.util.List;

public interface BuyerService {

    //查询一个订单
    OrderDTO findOrderOne(String openid , String orderId);
    //查询订单列表
    List<OrderDTO> findOrderList(String openid);
    //取消订单
    OrderDTO cancelOrder(String openid , String orderId);

}
