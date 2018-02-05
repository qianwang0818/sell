package com.imooc.utils;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class CookieUtils {

    /**生成并设置cookie,传入Response和1键,2值,3有效时间,4有效范围*/
    public static void set(HttpServletResponse response, String key, String value, int time, String path){
        Cookie cookie = createCookie(key, value, time, path);
        response.addCookie(cookie);
    }
    /**生成并设置cookie,传入Response和1键,2值,3有效时间*/
    public static void set(HttpServletResponse response, String key, String value, int time){
        Cookie cookie = createCookie(key, value, time);
        response.addCookie(cookie);
    }
    /**生成并设置cookie,传入Response和1键,2值,3有效范围*/
    public static void set(HttpServletResponse response, String key, String value, String path){
        Cookie cookie = createCookie(key, value, path);
        response.addCookie(cookie);
    }
    /**生成并设置cookie,传入Response和1键,2值*/
    public static void set(HttpServletResponse response, String key, String value){
        Cookie cookie = createCookie(key, value);
        response.addCookie(cookie);
    }

    /**得到cookie,传入1键,2值,3有效时间,4有效范围*/
    public static Cookie createCookie(String key, String value, int time, String path){
        Cookie cookie = new Cookie(key, value);	//新建一个cookie
        cookie.setMaxAge(time);						//设置有效时间
        if(path!=null){
            cookie.setPath(path);					//如果有有效范围,设置有效范围
        }
        return cookie;
    }

    /**得到cookie,传入1键,2值,3有效时间*/
    public static Cookie createCookie(String key, String value, int time){
        return createCookie(key,value,time,null);
    }

    /**得到cookie,传入1键,2值,3有效范围*/
    public static Cookie createCookie(String key, String value, String path){
        Cookie cookie = new Cookie(key, value);	//新建一个cookie
        if(path!=null){
            cookie.setPath(path);					//如果有有效范围,设置有效范围
        }
        return cookie;
    }

    /**得到cookie,传入1键,2值*/
    public static Cookie createCookie(String key, String value){
        return createCookie(key,value,null);
    }



    /**根据键和request对象,获得request对象中Cookie中保存的值*/
    public static String getCookieValue(HttpServletRequest request, String key){

        Cookie cookie = readCookieMap(request).get(key);
        String value = null;
        if (cookie!=null){
            value = cookie.getValue();
        }
        return value;

        /*Cookie[] cookies = request.getCookies();
        String value = null;
        if(cookies!=null){
            for (Cookie c : cookies) {
                if(c.getName().equals(key)){
                    value = c.getValue();
                }
            }
        }
        return value;*/

    }


    /**根据键和request对象,获得request对象中的Cookie*/
    public static Cookie getCookie(HttpServletRequest request, String key){
        return readCookieMap(request).get(key);
    }

    /**把Cookie数组转换成map,方便获取*/
    private static Map<String, Cookie> readCookieMap(HttpServletRequest request){
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for (Cookie c : cookies) {
                cookieMap.put(c.getName(),c);
            }
        }
        return cookieMap;
    }

}
