package com.imooc.controller;

import com.imooc.VO.ProductInfoVO;
import com.imooc.VO.ProductVO;
import com.imooc.VO.ResultVO;
import com.imooc.domain.ProductCategory;
import com.imooc.domain.ProductInfo;
import com.imooc.service.CategoryService;
import com.imooc.service.ProductService;
import com.imooc.utils.ResultVOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO list(){

        //1.查询所有上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        //2.根据所有上架商品,查询类目,(一次性查询,传入List,用in关键字查询)
        //2.1 将 List<ProductInfo> 转成 List<Integer> .有两种方式:
        //2.1.1 传统方式: for循环
        /*List<Integer> categoryTypeList = new ArrayList<Integer>();
        for (ProductInfo productInfo : productInfoList) {categoryTypeList.add(productInfo.getCategoryType());}*/
        //2.1.2 JDK1.8方式: lambda表达式
        List<Integer> categoryTypeList = productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
        //2.2 将List<Integer>作为in查询的参数查询所有类目
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        //3.将数据库查到的结果拼装成Json最外层的data集合属性
        List<ProductVO> data = new ArrayList<>();   //data属性,是一个Json集合,定义一个List容器
        //遍历封装data外层的ProductVO,塞进data集合
        for (ProductCategory category : productCategoryList) {

            List<ProductInfoVO> productInfoVOList = new ArrayList<ProductInfoVO>(); //视图商品详情ProductInfoVO的容器
            ProductVO productVO = new ProductVO(category.getCategoryName(),category.getCategoryType()+"",productInfoVOList);  //全参构造
            data.add(productVO);   //塞进data集合

            //遍历封装内层的视图商品详情ProductInfoVO对象,塞入预先准备好的List容器
            for (ProductInfo product : productInfoList) {
                //添加条件: 当前遍历到的商品属于当前类目
                if(product.getCategoryType()==category.getCategoryType()){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    //调用工具类,拷贝属性值
                    BeanUtils.copyProperties(product,productInfoVO);
                    productInfoVOList.add(productInfoVO);   //塞进视图商品详情的List容器
                }
            }
        }

        //4.封装得到Json所需的ResultVO对象
        return ResultVOUtils.success(data);
    }

}
