package com.snake.system.security;

import com.base.Constants;
import com.snake.system.model.Role;
import com.snake.system.service.IRoleService;
import com.snake.system.model.User;
import com.snake.system.service.IUserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义登录用户详细信息设置,组装用户的权限
 *
 * @author suxl
 * @Date 2014-05-28
 */
public class MyUserDetailServiceImpl implements UserDetailsService {
    protected static Log log = LogFactory.getLog(MyUserDetailServiceImpl.class);

    @Resource(name = "roleService")
    private IRoleService roleService;

    @Resource(name = "userService")
    private IUserService userService;

    // 登录验证
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        try {
            user = this.userService.getUserByLoginName(username);
//            List<Role> roles = roleService.getListByUserId(user.getId());
            Role role = roleService.getObject(user.getRoleId());
            List<Role> roles = new ArrayList<Role>();
            roles.add(role);
            roles.add(new Role(Constants.ROLE_LOGIN_USER));
            user.setRoles(roles);
        } catch (UsernameNotFoundException e) {
            log.error(e);
        } catch (Exception e) {
            log.error(e);
        }
        return user;
    }
}
