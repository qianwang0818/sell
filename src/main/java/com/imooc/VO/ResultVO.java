package com.imooc.VO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**Http请求返回的最外层对象*/
@Data
public class ResultVO<T> {

    /**状态码: 正常0 , 异常 */
    private Integer Code;

    /**提示信息*/
    private String msg;

    /**具体内容*/
    private T data;

}
