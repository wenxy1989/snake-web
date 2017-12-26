package com.base.util;

import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * User: qufengfu
 * Date: 13-8-3
 */
public class CookieUtils {
    protected transient static Logger logger = Logger.getLogger(CookieUtils.class);
    /**
     * 设置cookie
     * @param response
     * @param name cookie名字
     * @param value cookie值
     * @param domain cookie所在的域
     * @param maxAge cookie生命周期  以秒为单位
     */
    public static void setCookie(HttpServletResponse response, String name, String value, String domain, int maxAge) {
        try{
            if (value == null) {
                value = "";
            }
            value = URLEncoder.encode(value, "UTF-8");
            Cookie cookie = new Cookie(name, value);
            cookie.setMaxAge(maxAge);
            if (domain != null && !"".equals(domain)) {
                cookie.setDomain(domain);
            }
            cookie.setPath("/");
            response.addCookie(cookie);
        }catch (Exception e){
            logger.error(e);
        }
    }

    /**
     * 获取cookie对象
     * @param request
     * @param name  cookie的名字
     * @return
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie cookies[] = request.getCookies();
        // Return null if there are no cookies or the name is invalid.
        if (cookies == null || name == null || name.length() == 0) {
            return null;
        }
        // Otherwise, we do a linear scan for the cookie.
        Cookie cookie = null;
        for (int i = 0; i < cookies.length; i++) {
            // If the current cookie name matches the one we're looking for,
            // we've
            // found a matching cookie.
            if (cookies[i].getName().equals(name)) {
                cookie = cookies[i];
                // The best matching cookie will be the one that has the correct
                // domain name. If we've found the cookie with the correct
                // domain name,
                // return it. Otherwise, we'll keep looking for a better match.
                break;
            }
        }
        return cookie;
    }

    /**
     * 根据cookie名称获取cookie的值
     * @param request
     * @param name
     * @return
     */
    public static String getValue(HttpServletRequest request, String name){
        String result = null;
        try{
            Cookie cookie = getCookie(request,name);
            if(cookie!=null && !"".equals(cookie.getValue())){
                result = URLDecoder.decode(cookie.getValue(),"UTF-8");
            }
        }catch (Exception e){
            logger.error(e);
        }
        return result;
    }
}
