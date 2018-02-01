package com.imooc.enums;

import lombok.Getter;

@Getter
public enum ProductStatusEnum implements CodeEnum {

    UP(0,"在架"),      //正常上架
    DOWN(1,"下架")     //下架
    ;

    private Integer code;
    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
