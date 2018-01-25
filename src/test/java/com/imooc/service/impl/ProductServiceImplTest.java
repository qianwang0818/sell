package com.imooc.service.impl;

import com.imooc.domain.ProductInfo;
import com.imooc.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

 @RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;

    @Test
    public void findOne() throws Exception {
        ProductInfo productInfo = productService.findOne("8a7e8618611c969801611c96a5c90000");
        Assert.assertEquals("8a7e8618611c969801611c96a5c90000",productInfo.getProductId());
    }

    @Test
    public void findByProductStatus() throws Exception {
        List<ProductInfo> productInfos = productService.findUpAll();
        Assert.assertNotEquals(0,productInfos.size());
    }

    @Test
    public void findAll() throws Exception {
        Page<ProductInfo> page = productService.findAll(null);
        Assert.assertNotEquals(0,page.getTotalElements());
    }

    @Test
    public void save() throws Exception {
        ProductInfo productInfo = new ProductInfo("黄金脆皮鸡(半只)", new BigDecimal(15.0), 50,
                "金香酥脆,非常美味", "http://xxx.jpg",  ProductStatusEnum.DOWN.getCode(), 2);
        ProductInfo result = productService.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void delete() throws Exception {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("8a7e8618611cbcaa01611cbcb8120000");
        productService.delete(productInfo);
    }

}