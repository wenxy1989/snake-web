package com.snake.system.controller;

import com.base.Constants;
import com.snake.system.model.User;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wen on 2015/3/10.
 */
public class BasicController {

    protected transient Logger logger = Logger.getLogger(getClass());

    public static final String RESULT_SUCCESS = "success";
    public static final String RESULT_ERROR = "error";
    public static final String RESULT_EXISTS = "exists";
    public static String RESULT_ADD_SUCCESS = "add_success";
    public static String RESULT_EDIT_SUCCESS = "edit_success";
    public static String RESULT_DELETE_SUCCESS = "delete_success";

    public static String OPE_RESULT = "ope_result";
    public static String OPE_NUMBER = "ope_number";

    public static User sysUser(){
        User user = null;
        try{
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(principal instanceof UserDetails){
                user = (User)principal;
            }else{
                user = new User();
                user.setUserName(principal.toString());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return user;
    }

    protected void responseTip(ModelAndView mv, HttpServletRequest request){
        String ope_result = request.getParameter(OPE_RESULT);
        if(StringUtils.isNotBlank(ope_result)){
            mv.addObject(OPE_RESULT,ope_result + "," + System.currentTimeMillis());
        }
    }

    protected StringBuffer getBaseUrl(HttpServletRequest request){
        StringBuffer sb = new StringBuffer("redirect:");
        if(null != request){
            sb.append(request.getScheme());
            sb.append("://");
            sb.append(request.getServerName());
            sb.append(":");
            sb.append(request.getServerPort());
            sb.append(request.getContextPath());
            sb.append("/");
        }
        return sb;
    }

    protected User getLoginUser(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute(Constants.USER_SESSON_KEY);
        return user;
    }

    protected Long getLoginUserId(HttpServletRequest request) {
        Long userId = null;
        User user = getLoginUser(request);
        if(null != user){
            userId = user.getId();
        }
        return userId;
    }

    protected Long getLoginUserRoleId(HttpServletRequest request) {
        Long roleId = null;
        User user = getLoginUser(request);
        if(null != user){
            roleId = user.getRoleId();
        }
        return roleId;
    }

}
