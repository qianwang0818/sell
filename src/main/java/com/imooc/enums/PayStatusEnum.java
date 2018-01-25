package com.imooc.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum {

    UNPAY(0,"未支付,等待支付"),           //未支付,等待支付
    PAID(1,"已支付,支付成功"),            //已支付,支付成功
    ;

    private Integer code;
    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
