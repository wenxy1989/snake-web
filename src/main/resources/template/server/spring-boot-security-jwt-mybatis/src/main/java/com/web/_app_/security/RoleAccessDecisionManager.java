package com.web.system.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.csrf.CsrfException;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class RoleAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> configs) throws AccessDeniedException, InsufficientAuthenticationException {
        if (null == authentication) {
            throw new AuthorizationServiceException("authentication is null");
        }
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (null == authorities) {
            throw new AccessDeniedException("role is null");
        }
        if (null == configs) {
            return;
        }
        for (GrantedAuthority authority : authorities) {
            for (ConfigAttribute config : configs) {
                if (authority.getAuthority().equals(config.getAttribute())) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("authentication has no permission");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return false;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
