package com.web.system.security;

import com.web.system.entity.User;
import com.web.system.service.UserService;
import com.web.system.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

@Component
public class UsernameAuthenticationManager implements AuthenticationManager {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        User user = this.userService.loadUserByUsername(username);
        String encodePassword = MD5Util.encode(password);
        if (null != user) {
            if (user.getPassword().equals(encodePassword)) {
                if (!user.isAccountNonExpired()) {
                    throw new AccountExpiredException("account expired");
                } else if (!user.isAccountNonLocked()) {
                    throw new LockedException("account locked");
                } else if (!user.isCredentialsNonExpired()) {
                    throw new CredentialsExpiredException("credentials expired");
                } else if (!user.isEnabled()) {
                    throw new DisabledException("account disabled");
                } else {
                    return new UsernamePasswordAuthenticationToken(username, null, authorities());
                }
            } else {
                throw new BadCredentialsException("credentials error");
            }
        }else{
            throw new AuthenticationCredentialsNotFoundException("account not found");
        }
    }


    private Collection<GrantedAuthority> authorities() {
        return Arrays.asList(new SimpleGrantedAuthority("role"));//todo
    }

}
