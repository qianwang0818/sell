package com.imooc.controller;

import com.imooc.config.WechatAccountConfig;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;


@Controller
@RequestMapping("/wechat")
@Slf4j
public class WechatController {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl){
        log.info("收到authorize方法的请求,returnUrl={}",returnUrl);
        //1.WxMpService配置: 使用配置类WechatMpConfig并注册WxMpService
        //2.调用WxMpService方法获得重定向的url
        String natappUrl = wechatAccountConfig.getNatappUrl();
        String url = natappUrl+"/sell/wechat/userInfo";    //重定向的目标网址
        //String url = "https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421137522";
        String scope = WxConsts.OAuth2Scope.SNSAPI_USERINFO;
        String state = URLEncoder.encode(returnUrl);    //传到微信端,会原样传回来的字符串
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, scope, state);
        log.info("【微信网页授权】获取code,redirectUrl{}",redirectUrl);
        //3.执行重定向
        return "redirect:" + redirectUrl;
    }

    /**被authorize重定向的方法,可以获得用户信息*/
    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code, @RequestParam("state") String returnUrl){
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("微信网页授权】{}",e);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();
        //returnUrl = "www.sina.com";
        return "redirect:" + returnUrl + "?openid=" + openId;
    }
}
