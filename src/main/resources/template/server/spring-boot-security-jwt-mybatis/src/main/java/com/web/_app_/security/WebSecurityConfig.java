package com.web.system.security;

import com.web.system.security.RequestAccessDeniedHandler;
import com.web.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;
    @Autowired
    private RequestAccessDeniedHandler requestAccessDeniedHandler;
    @Autowired
    private UsernameAuthenticationManager usernameAuthenticationManager;
    @Autowired
    private ObjectPostProcessor objectPostProcessor;
    @Autowired
    private AuthenticationEntryPoint authenticationFailureEntryPoint;
    @Autowired
    private FilterInvocationSecurityMetadataSource uriSecurityMetadataSource;
    @Autowired
    private AccessDecisionManager roleAccessDecisionManager;

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return this.usernameAuthenticationManager;
    }

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return this.userService;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
//        web.expressionHandler()
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
        filterSecurityInterceptor.setSecurityMetadataSource(this.uriSecurityMetadataSource);
        filterSecurityInterceptor.setAccessDecisionManager(this.roleAccessDecisionManager);
        UsernamePasswordAuthenticationFilter loginFilter = new JwtLoginFilter();
        loginFilter.setAuthenticationManager(this.authenticationManager());
        http.cors().and()
                .csrf().disable()
                .formLogin().loginProcessingUrl("/login").and()
                .addFilter(filterSecurityInterceptor)
                .addFilter(loginFilter)
                .addFilter(new JwtAuthenticationFilter(this.authenticationManager()))
                .exceptionHandling().accessDeniedHandler(this.requestAccessDeniedHandler).authenticationEntryPoint(authenticationFailureEntryPoint).and()
                .authorizeRequests().withObjectPostProcessor(this.objectPostProcessor).anyRequest().authenticated();
    }

    @Override
    public void setAuthenticationConfiguration(AuthenticationConfiguration authenticationConfiguration) {
        super.setAuthenticationConfiguration(authenticationConfiguration);
    }
}
