package com.snake.system.security;

import com.base.Constants;
import com.base.util.MD5Util;
import com.snake.system.model.User;
import com.snake.system.service.IUserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 用户身份鉴定过滤器
 * User: suxl
 * Date: 2014-5-28
 */
public class MyAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    protected static Log log = LogFactory.getLog(MyAuthenticationFilter.class);

	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";

    @Resource(name="userService")
	private IUserService userService;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}

		String username = obtainUsername(request);//获取用户提交的登录名
		String password = obtainPassword(request);//获取用户提交的登录密码

        request.getSession().setAttribute("username",username);

		// 验证用户账号与密码是否正确
		username = username.trim();
        User user = null;
        try {
            user = this.userService.getUserByLoginName(username);
        } catch (Exception e) {
            log.error("根据登录名获取用户信息失败!",e);
        }

        String md5Pwd = null;
        if (user != null) {
            md5Pwd = MD5Util.encode(password.toLowerCase());
            if(!user.getLoginPwd().equals(md5Pwd)){
                BadCredentialsException exception = new BadCredentialsException("error_user_name_or_password");
                //request.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, exception);
                throw exception;
            }
        }else{
            BadCredentialsException exception = new BadCredentialsException("no_user_with_this_user_name");
            //request.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, exception);
            throw exception;
        }

		//将用户放入session中
		HttpSession session = request.getSession();
		session.setAttribute(Constants.USER_SESSON_KEY, user);

		// 实现 Authentication
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, md5Pwd);
		// 允许子类设置详细属性
		setDetails(request, authRequest);

		// 运行UserDetailsService的loadUserByUsername 再次封装Authentication
		return this.getAuthenticationManager().authenticate(authRequest);
	}

	@Override
	protected String obtainUsername(HttpServletRequest request) {
		Object obj = request.getParameter(USERNAME);
		return null == obj ? "" : obj.toString();
	}

	@Override
	protected String obtainPassword(HttpServletRequest request) {
		Object obj = request.getParameter(PASSWORD);
		return null == obj ? "" : obj.toString();
	}
	
	@Override
	protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
		super.setDetails(request, authRequest);
	}
}
