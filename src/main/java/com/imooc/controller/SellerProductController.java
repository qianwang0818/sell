package com.imooc.controller;

import com.imooc.domain.ProductCategory;
import com.imooc.domain.ProductInfo;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.form.ProductForm;
import com.imooc.service.CategoryService;
import com.imooc.service.ProductService;
import com.imooc.utils.KeyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**卖家端商品控制层*/
@Controller
@RequestMapping("/seller/product")
@Slf4j
public class SellerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

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

    /**商品数据回显,相当于editUI*/
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,
                                Map<String,Object> map){
        if(!StringUtils.isEmpty(productId)){    //商品ID不为空,去查询这个商品
            ProductInfo productInfo = productService.findOne(productId);
            map.put("productInfo",productInfo);
        }
        //查询所有的类目,类目下拉框需要
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList",categoryList);

        return new ModelAndView("product/index",map);
    }

    /**保存or修改 商品*/
    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm productForm,
                             BindingResult bindingResult,
                             Map<String, Object> map){
        if(bindingResult.hasErrors()){
            log.error("【商品保存/修改】表单验证有误,错误信息:{}",bindingResult.getFieldError().getDefaultMessage());
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("common/error",map);
        }
        try {
            String productId = productForm.getProductId();
            ProductInfo productInfo = null;
            if(StringUtils.isEmpty(productId)){    //新增商品
                productInfo = new ProductInfo();
                productInfo.setProductId(KeyUtils.getUniqueKey());
            }else{                                                  //修改商品
                productInfo = productService.findOne(productId);
                if(productInfo==null){
                    log.error("【商品保存/修改】需要修改的商品ID有误,productId:{}", productId);
                    throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
                }
                productInfo.setUpdateTime(null);
            }
            BeanUtils.copyProperties(productForm,productInfo);
            productService.save(productInfo);
        } catch (Exception e) {
            log.error("【商品保存/修改】发生异常,错误信息:{}",e.getMessage());
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("common/error",map);
        }
        map.put("msg","保存/修改成功");
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);


    }


}
