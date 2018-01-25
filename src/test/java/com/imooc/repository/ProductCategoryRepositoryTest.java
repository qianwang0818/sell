package com.imooc.repository;

import com.imooc.domain.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryRepositoryTest {
    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Test
    public void findOneTest(){
        ProductCategory category = productCategoryRepository.findOne(1);
        log.info(category.toString());
    }

    @Test
    @Transactional
    public void save(){
        ProductCategory category = new ProductCategory("地狱",60);
        ProductCategory result = productCategoryRepository.save(category);
        Assert.assertNotNull(result);
    }

    @Test
    public void update(){
        ProductCategory category = productCategoryRepository.findOne(2);
        category.setCategoryName("后宫");
        category.setCategoryType(20);
        productCategoryRepository.save(category);
    }

    @Test
    public void findByCategoryTypeIn() throws Exception {
        List<Integer> list = Arrays.asList(20,30,40);
        List<ProductCategory> categories = productCategoryRepository.findByCategoryTypeIn(list);
        for (ProductCategory category : categories) {
            log.info(category.toString());
        }
    }

}