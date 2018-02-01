package com.imooc.service;

import com.imooc.DTO.CartDTO;
import com.imooc.domain.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ProductInfo findOne(String productId);

    /**查询正常上架商品*/
    List<ProductInfo> findUpAll();

    /**查询所有商品(不区分上下架)*/
    Page<ProductInfo> findAll(Pageable pageable);

    /**商品新增*/
    ProductInfo save(ProductInfo productInfo);

    /**商品删除*/
    void delete(ProductInfo productInfo);

    /**增加库存*/
    void increaseStock(List<CartDTO> cartDTOList);

    /**减少库存*/
    void decreaseStock(List<CartDTO> cartDTOList);

    /**商品上架*/
    ProductInfo onSale(String productId);

    /**商品下架*/
    ProductInfo offSale(String productId);
}
