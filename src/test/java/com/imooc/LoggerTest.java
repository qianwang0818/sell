package com.imooc;

import com.imooc.DTO.CartDTO;
import com.imooc.domain.OrderDetail;
import com.imooc.domain.OrderMaster;
import com.imooc.repository.OrderDetailRepository;
import com.imooc.repository.OrderMasterRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {

    @Autowired
    private OrderDetailRepository orderItemRepository;


    @Test
    public void test(){
        log.debug("debug.....");
        log.info("info.....");
        log.warn("warn.....");
        log.error("error.....");
    }

    @Test
    public void lambda(){

        //准备数据
        List<OrderDetail> orderItemList = orderItemRepository.findAll();
        for (OrderDetail orderItem : orderItemList) {
            log.error("OrderItem:{}",orderItem.toString());
        }

        log.info("----------分割线---------");
        //需求: List<OrderDetail> orderItemList  -->  List<CartDTO> cartList1

        //普通青年
        List<CartDTO> cartList1 = new ArrayList<CartDTO>();
        for (OrderDetail orderItem : orderItemList) {
            String pid = orderItem.getProductId();
            Integer count = orderItem.getProductQuantity();
            CartDTO cartDTO = new CartDTO(pid,count);
            cartList1.add(cartDTO);
        }
        for (CartDTO cart : cartList1) {
            log.error("普通青年:{}",cart.toString());
        }

        log.info("----------分割线---------");
        //文艺青年: λ表达式
        List<CartDTO> cartList2 = orderItemList.stream().map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        for (CartDTO cart : cartList2) {
            log.error("文艺青年:{}",cart.toString());
        }
    }

}
