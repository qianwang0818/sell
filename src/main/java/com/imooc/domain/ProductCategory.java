package com.imooc.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "product_category", schema = "sell", catalog = "")
@DynamicUpdate  //针对mysql的自更新时间字段.当其它字段有更新时,会自动更新updateTime的时间
@Data
@NoArgsConstructor
public class ProductCategory {
    @Id
    @GeneratedValue
    private Integer categoryId;
    @Column(name = "category_name")
    private String categoryName;
    @Column(name = "category_type")
    private Integer categoryType;

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
