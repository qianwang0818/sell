package com.imooc.service.impl;

import com.imooc.DTO.CartDTO;
import com.imooc.DTO.OrderDTO;
import com.imooc.converter.OrderMasterToOrderDTOConverter;
import com.imooc.domain.OrderDetail;
import com.imooc.domain.OrderMaster;
import com.imooc.domain.ProductInfo;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayStatusEnum;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.repository.OrderDetailRepository;
import com.imooc.repository.OrderMasterRepository;
import com.imooc.repository.ProductInfoRepository;
import com.imooc.service.OrderService;
import com.imooc.service.ProductService;
import com.imooc.utils.KeyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;


    /**创建订单*/
    @Override
    public OrderDTO create(OrderDTO orderDTO) {

        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);    //定义一个商品总价的容器,起始值为零,遍历每条商品详情累加金额.
        String orderId = KeyUtils.getUniqueKey();   //生成随机的订单Id
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();

        //1.查询商品(数量,价格)
        for (OrderDetail orderDetail : orderDetailList) {
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if(productInfo==null){  //如果根据pid找不到相应的商品
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2.计算订单总价:  商品总价 += 商品单价*商品数量
            BigDecimal price = productInfo.getProductPrice();   //数据库查出的单价,不是前端传过来的
            Integer productQuantity = orderDetail.getProductQuantity();
            orderAmount = price.multiply(new BigDecimal(productQuantity)).add(orderAmount);
            //3.数据库遍历添加订单详情OrderDetail
            //orderDetail.setDetailId(KeyUtils.getUniqueKey()); //有注解uuid
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo,orderDetail);  //内省工具类
            orderDetailRepository.save(orderDetail);
        }

        //3.数据库添加订单OrderMaster
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster); //先拷贝字段,再做下面的赋值,以免覆盖
        orderMaster.setOrderId(orderId);            //赋值
        orderMaster.setOrderAmount(orderAmount);    //赋值
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());  //被null覆盖了,重新赋值
        orderMaster.setPayStatus(PayStatusEnum.UNPAY.getCode());  //被null覆盖了,重新赋值
        orderMasterRepository.save(orderMaster);

        //4.扣除库存
        List<CartDTO> cartDTOList = orderDetailList.stream().map(e -> new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);

        //发送WebSocket消息
        //webSocket.sendMessage(orderDTO.getOrderId());

        return orderDTO;
    }

    /**查询单个订单*/
    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if(orderMaster==null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        //封装OrderDTO
        OrderDTO orderDTO = OrderMasterToOrderDTOConverter.convert(orderMaster);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    /**查询订单列表*/
    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        //查到Page<OrderMaster>
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid,pageable);
        //将List<OrderMaster>转换成List<OrderDTO>
        List<OrderDTO> orderDTOList = OrderMasterToOrderDTOConverter.convert(orderMasterPage.getContent());
        //构造Page<OrderDTO>对象并返回
        Page<OrderDTO> orderDTOPage = new PageImpl<>(orderDTOList, pageable, orderMasterPage.getTotalElements());
        return orderDTOPage;
    }

    /**取消订单*/
    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    /**完结订单*/
    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    /**支付订单*/
    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
