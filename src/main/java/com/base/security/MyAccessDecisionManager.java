package com.base.security;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class MyAccessDecisionManager implements AccessDecisionManager {

    //In this method, need to compare authentication with configAttributes.
    // 1, A object is a URL, a filter was find permission configuration by this URL, and pass to here.
    // 2, Check authentication has attribute in permission configuration (configAttributes)
    // 3, If not match corresponding authentication, throw a AccessDeniedException.
    public void decide(Authentication user, Object object, Collection<ConfigAttribute> roles) throws AccessDeniedException, InsufficientAuthenticationException {
		if(roles == null || roles.size() < 1){
			return;//没有设置访问权限就可以访问
            //throw new AccessDeniedException("no right");
        }
        Iterator<ConfigAttribute> ite=roles.iterator();
        while(ite.hasNext()){
            ConfigAttribute ca=ite.next();
            String needRole= ca.getAttribute();
            for(GrantedAuthority ga:user.getAuthorities()){
            	/**
            	 * 用户有url可以访问的角色或者ROLE_NONE可以访问此url
            	 */
                if(needRole.equals(ga.getAuthority()) || "ROLE_NONE".endsWith(needRole)){  //ga is user's role.
                    return;
                }
            }
        }
        throw new AccessDeniedException("no right");
    }

    public boolean supports(ConfigAttribute auth) {
        // TODO Auto-generated method stub
        return true;
    }

    public boolean supports(Class<?> clazz) {
        return true;
    }


}