package com.imooc.repository;

import com.imooc.domain.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {
    /**查询某订单下的所有订单详情,根据订单Id查询List*/
    List<OrderDetail> findByOrOrderId(String orderId);
}
