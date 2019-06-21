package com.web.system.service;

import com.web.system.entity.User;
import com.web.system.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserMapper base_userMapper;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        user.setLoginName(username);
        return base_userMapper.selectOne(user);
    }
}
