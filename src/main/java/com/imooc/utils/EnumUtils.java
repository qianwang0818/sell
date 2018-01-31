package com.imooc.utils;

import com.imooc.enums.CodeEnum;

public class EnumUtils {
    public static <T extends CodeEnum> T getByCode(Integer code , Class<T> enumClass){
        for (T t : enumClass.getEnumConstants()) {
            if(code.equals(t.getCode())){
                return t;
            }
        }
        return null;
    }
}
