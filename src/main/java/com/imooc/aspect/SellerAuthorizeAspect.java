package com.imooc.aspect;

import com.imooc.constant.CookieConstant;
import com.imooc.constant.RedisConstant;
import com.imooc.exception.SellAuthorizeException;
import com.imooc.exception.SellException;
import com.imooc.utils.CookieUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("execution(public * com.imooc.controller.Seller*.*(..)) && !execution(public * com.imooc.controller.SellerUserController.*(..))")
    public void verify(){};

    @Before("verify()")
    public void beforeVerify(){
        //拿到request
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //查询cookie,得到token
        String token = CookieUtils.getCookieValue(request, CookieConstant.TOKEN);
        if(StringUtils.isEmpty(token)){
            log.warn("【登陆校验】Cookie中找不到token(as value)");
            throw new SellAuthorizeException();
        }
        //查询redis,根据token得到openid
        String openid = redisTemplate.opsForValue().get(RedisConstant.TOKEN_PREFIX + token);
        if(StringUtils.isEmpty(openid)){
            log.warn("【登陆校验】Redis中找不到token(as key)");
            throw new SellAuthorizeException();
        }
    }

}
