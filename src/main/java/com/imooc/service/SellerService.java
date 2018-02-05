package com.imooc.service;

import com.imooc.domain.SellerInfo;


public interface SellerService {
    SellerInfo findSellerInfoByOpenid(String openid);
}
