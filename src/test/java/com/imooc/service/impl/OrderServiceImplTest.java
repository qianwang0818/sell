package com.imooc.service.impl;

import com.imooc.DTO.OrderDTO;
import com.imooc.domain.OrderDetail;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final String BUYER_OPENID = "110110";

    @Test
    public void create() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerAddress("中粮");
        orderDTO.setBuyerName("表哥");
        orderDTO.setBuyerOpenid(BUYER_OPENID);
        orderDTO.setBuyerPhone("10086");
        List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
        orderDTO.setOrderDetailList(orderDetailList);

        OrderDetail o1 = new OrderDetail();
        o1.setProductId("8a7e8618611c969801611c96a5c90000");
        o1.setProductQuantity(2);
        orderDetailList.add(o1);

        OrderDTO result = orderService.create(orderDTO);

        log.info("[创建订单]result{}",result);


    }

    @Test
    public void findOne() throws Exception {
    }

    @Test
    public void findList() throws Exception {
    }

    @Test
    public void cancel() throws Exception {
    }

    @Test
    public void finish() throws Exception {
    }

    @Test
    public void paid() throws Exception {
    }

}