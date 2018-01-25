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

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    void delete(ProductInfo productInfo);

    /**减少库存*/
    void decreaseStock(List<CartDTO> cartDTOList);

    Integer findProductStockByProductId(String productId);

    /**增加库存*/

}
