package com.imooc.converter;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.imooc.DTO.OrderDTO;
import com.imooc.domain.OrderDetail;
import com.imooc.domain.OrderMaster;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.form.OrderForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class OrderConverter {

    public static OrderDTO convertFromOrderMasterToOrderDTO(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convertFromOrderMasterToOrderDTO(List<OrderMaster> orderMasterList){
        return orderMasterList.stream().map( e -> convertFromOrderMasterToOrderDTO(e)).collect(Collectors.toList());
    }

    public static OrderMaster convertFromOrderDTOToOrderMaster(OrderDTO orderDTO){
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        return orderMaster;
    }

    public static List<OrderMaster> convertFromOrderDTOToOrderMaster(List<OrderDTO> orderDTOList){
        return orderDTOList.stream().map( e -> convertFromOrderDTOToOrderMaster(e)).collect(Collectors.toList());
    }

    public static OrderDTO convertFromOrderFormToOrderDTO(OrderForm orderForm){
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        //把Json格式的购物车item字符串,转成List<OrderDetail>,使用Gson
        String json = orderForm.getItems();
        List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
        Gson gson = new Gson();
        try {
            orderDetailList = gson.fromJson(json , new TypeToken< List<OrderDetail> >(){}.getType());
        } catch (JsonSyntaxException e) {
            log.error("【对象转换】Json->Object转换失败,json={}",json);
            log.error("异常信息:{}",e.toString());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

}
