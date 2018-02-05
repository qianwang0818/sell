package com.imooc.exception;

import com.imooc.enums.ResultEnum;

/**
 * 想象有这样的接口需求:
 * 统一异常捕获处理后,响应头是200.
 * 有时候会要求异常与非异常要有状态区分.
 * 例如银行接口要求异常的响应头为非200.
 */
public class ResponseBankException extends RuntimeException {
    private Integer code;

    public ResponseBankException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public ResponseBankException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
