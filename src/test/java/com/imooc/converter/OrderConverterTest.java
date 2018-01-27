package com.imooc.converter;

import com.imooc.DTO.OrderDTO;
import com.imooc.form.OrderForm;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

@Slf4j
public class OrderConverterTest {

    private String item = "[{productId: '8a7e8618611c969801611c96a5c90000',productQuantity: 2 }]";

    @Test
    public void convertFromOrderMasterToOrderDTO() throws Exception {
    }

    @Test
    public void convertFromOrderMasterToOrderDTO1() throws Exception {
    }

    @Test
    public void convertFromOrderDTOToOrderMaster() throws Exception {
    }

    @Test
    public void convertFromOrderDTOToOrderMaster1() throws Exception {
    }

    @Test
    public void convertFromOrderFormToOrderDTO() throws Exception {
        OrderForm orderForm = new OrderForm();
        orderForm.setAddress("天桥下");
        orderForm.setName("张三");
        orderForm.setOpenid("333");
        orderForm.setPhone("119");
        orderForm.setItems(item);   //如果传入空串,转换会得到orderDetailList=null
        OrderDTO orderDTO = OrderConverter.convertFromOrderFormToOrderDTO(orderForm);
        log.info("orderDTO:{}"+orderDTO);

    }

}