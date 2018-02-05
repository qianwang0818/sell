package com.imooc.service;

import com.imooc.DTO.OrderDTO;

public interface PushMessageService {
    /**订单状态变更推送*/
    void orderStatus(OrderDTO orderDTO);
}
