package com.web.system.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.system.config.ResponseBodyResult;
import com.web.system.entity.User;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            if(null == user.getUsername()){
                throw new UsernameNotFoundException("login_name not found");
            }
            if(null == user.getPassword()){
                throw new PreAuthenticatedCredentialsNotFoundException("login_pwd not found");
            }
            return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        } catch (IOException e) {
            throw new BadCredentialsException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = JwtTokenUtil.createToken(new ObjectMapper().writeValueAsString(authResult));
        ResponseBodyResult result = ResponseBodyResult.success(String.format("%s %s",JwtTokenUtil.getTokenPrefix(),token));
//        super.successfulAuthentication(request, response, chain, authResult);
        write(response, result);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        ResponseBodyResult result = ResponseBodyResult.error(1, failed.getMessage());
        write(response, result);
//        super.unsuccessfulAuthentication(request, response, failed);
    }

    public static void write(HttpServletResponse response, Object result) throws IOException {
        response.setHeader("ContentType", MediaType.APPLICATION_JSON_UTF8_VALUE);
        Writer writer = response.getWriter();
        writer.write(new ObjectMapper().writeValueAsString(result));
    }
}
