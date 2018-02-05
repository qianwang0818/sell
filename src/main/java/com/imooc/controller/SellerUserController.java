package com.imooc.controller;

import com.imooc.config.ProjectUrlConfig;
import com.imooc.constant.CookieConstant;
import com.imooc.constant.RedisConstant;
import com.imooc.domain.SellerInfo;
import com.imooc.enums.ResultEnum;
import com.imooc.service.SellerService;
import com.imooc.utils.CookieUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/seller")
@Slf4j
public class SellerUserController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid,
                                HttpServletResponse response,
                                Map<String,Object> map){

        //1.openid去数据库做匹配
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
        if(sellerInfo==null){
            log.error("【卖家扫码登陆】登陆失败,登陆信息不正确,openid:{}"+openid);
            map.put("msg", ResultEnum.LOGIN_FAIL.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }

        //生成唯一token
        String token = UUID.randomUUID().toString();

        //2.设置token至redis
        String redisToken = RedisConstant.TOKEN_PREFIX + token;
        Integer redisExpire = RedisConstant.EXPIRE;
        redisTemplate.opsForValue().set(redisToken, openid, redisExpire, TimeUnit.SECONDS);

        //3.设置token至cookie
        String cookieToken = CookieConstant.TOKEN;
        Integer cookieExpire = CookieConstant.EXPIRE;
        CookieUtils.set(response,cookieToken,token,cookieExpire,"/");
        return new ModelAndView("redirect:"+ projectUrlConfig.getSell()+"/sell/seller/order/list");
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
                                HttpServletResponse response,
                                Map<String,Object> map){
        try {
            //1.从cookie里查询token
            String token = CookieUtils.getCookieValue(request, CookieConstant.TOKEN);
            if(!StringUtils.isEmpty(token)){  //request中找到这样的cookie,就开始做清除
                //2.清除redis的 前缀+token : openid
                redisTemplate.opsForValue().getOperations().delete(RedisConstant.TOKEN_PREFIX+token );
                //3.清除cookie的 固定键 : token
                CookieUtils.set(response,CookieConstant.TOKEN,null,0,"/");
            }
            map.put("msg",ResultEnum.LOGOUT_SUCCESS.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/success",map);
        } catch (Exception e) {
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("common/error",map);
        }

    }
}
