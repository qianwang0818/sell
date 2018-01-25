package com.imooc.utils;

import com.imooc.VO.ResultVO;

public class ResultVOUtils {

    /**返回成功状态,返回实际数据*/
    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("SUCCESS");
        resultVO.setData(object);
        return resultVO;
    }

    /**返回成功状态,无实际数据返回*/
    public static ResultVO success(){
        return success(null);
    }

    /**返回失败状态和失败信息,无实际数据返回*/
    public static ResultVO error(Integer code , String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }


}
