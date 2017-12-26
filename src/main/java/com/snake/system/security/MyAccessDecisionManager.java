package com.snake.system.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 自定义数据请求判定管理类
 * User: suxl
 * Date: 2014-5-28
 */
public class MyAccessDecisionManager implements AccessDecisionManager {

	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
		if (configAttributes != null) {
			//比较用户是否有操作功能的权限
			for (GrantedAuthority ga : authentication.getAuthorities()) {
				String userRoleCode = ga.getAuthority();//用户角色代码
				ConfigAttribute configAttribute =  new SecurityConfig(userRoleCode);
				if(configAttributes.contains(configAttribute)){//接受访问的角色判断
					return;//可以访问
				}
			}
            // 没有权限让我们去捕捉
            throw new AccessDeniedException("no_access_permission!");
		}
	}

	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

}
