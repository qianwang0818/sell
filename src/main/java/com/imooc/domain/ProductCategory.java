package com.imooc.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.imooc.utils.serializer.DateToLongSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

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
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date updateTime;
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date createTime;


    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
