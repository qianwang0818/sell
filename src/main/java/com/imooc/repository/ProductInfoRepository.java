package com.imooc.repository;

import com.imooc.domain.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {

    /**根据订单状态查询商品列表*/
    List<ProductInfo> findByProductStatus(Integer productStatus);

    /**查询商品库存*/
    @Query("select productStock from ProductInfo where productId = ?1")
    Integer findProductStockByProductId(String productId);

    /**减少库存*/
    @Modifying
    @Query("update ProductInfo set productStock=productStock - ?2 where productId = ?1 and productStock >= ?2 ")
    void reduceProductStock(String productId, Integer productQuantity);


}
