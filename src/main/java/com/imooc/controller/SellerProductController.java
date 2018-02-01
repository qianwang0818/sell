package com.imooc.controller;

import com.imooc.domain.ProductInfo;
import com.imooc.exception.SellException;
import com.imooc.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**卖家端商品控制层*/
@Controller
@RequestMapping("/seller/product")
@Slf4j
public class SellerProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value="page", defaultValue="1") Integer page ,
                                @RequestParam(value="size", defaultValue="10") Integer size,
                                Map<String,Object> map){
        Page<ProductInfo> productInfoPage = productService.findAll(new PageRequest(page - 1, size));
        map.put("productInfoPage",productInfoPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("product/list",map);
    }

    /**商品上架*/
    @GetMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId, Map<String,Object> map){
        try {
            ProductInfo productInfo = productService.onSale(productId);
        } catch (SellException e) {
            log.error("【商品上架】发生异常,错误信息:{}",e.getMessage());
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg","上架成功");
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }

    /**商品下架*/
    @GetMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId, Map<String,Object> map){
        try {
            ProductInfo productInfo = productService.offSale(productId);
        } catch (SellException e) {
            log.error("【商品下架】发生异常,错误信息:{}",e.getMessage());
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg","下架成功");
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }
}
