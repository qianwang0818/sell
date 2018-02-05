package com.imooc.handler;

import com.imooc.VO.ResultVO;
import com.imooc.config.ProjectUrlConfig;
import com.imooc.exception.ResponseBankException;
import com.imooc.exception.SellAuthorizeException;
import com.imooc.exception.SellException;
import com.imooc.utils.ResultVOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@Slf4j
public class SellExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    //拦截登录异常
    @ExceptionHandler(value = SellAuthorizeException.class)
    public ModelAndView handlerAuthorizeException(){
        return new ModelAndView(("redirect:"
                +projectUrlConfig.getSell()+"/sell/wechat/qrAuthorize")
                +"?returnUrl="+projectUrlConfig.getSell()+"/sell/seller/login");
    }

    /**拦截自定义SellException*/
    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellException(SellException e){
        return ResultVOUtils.error(e.getCode(),e.getMessage());
    }


    /**想象有这样的接口需求: 银行接口要求发生异常的响应头为非200*/
    @ExceptionHandler(value=ResponseBankException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN) //设置响应头状态码为403
    @ResponseBody
    public ResultVO handle(ResponseBankException e){
        //打印异常日志 方便程序员排错
        log.error("[银行接口异常]{}",e);
        //调用自定义工具类生成最外层对象
        return ResultVOUtils.error(110,e.getMessage());
    }


}
