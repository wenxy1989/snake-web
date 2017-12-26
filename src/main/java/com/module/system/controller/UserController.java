package com.module.system.controller;

import com.base.common.controller.AbstractController;
import com.base.utils.TimeTools;
import com.module.system.service.IParameterService;
import com.module.system.model.Role;
import com.module.system.service.IRoleService;
import com.module.system.model.User;
import com.module.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user/")
public class UserController extends AbstractController<User> {
	
	@Resource(name="userService")
	private IUserService service;

	@Resource(name="roleService")
	private IRoleService roleService;
	
	@Resource(name="parameterService")
	private IParameterService parameterService;
	
	@Autowired
    @Qualifier("authenticationManager")
    protected AuthenticationManager authenticationManager;

	@Override
	protected Class<User> getClassTemplate() {
		return User.class;
	}

	@Override
	protected IUserService getService() {
		return service;
	}

	@Override
	protected IParameterService getParameterService() {
		return parameterService;
	}

	@Override
	protected String getJspBasePath() {
		return "system/user/";
	}

	@Override
	protected String getBranchName() {
		return "user";
	}
	
	@RequestMapping(value = "list.do")
	public ModelAndView list(){
		return super.list();
	}
	
    @RequestMapping(value = "toCreate.do")
    public ModelAndView toCreate() {
    	return super.toCreate();
    }

    @RequestMapping(value = "toEdit.do")
    public ModelAndView toEdit(Long id) {
    	return super.toEdit(id);
    }

    @RequestMapping(value = "create.do", method = { RequestMethod.POST })
    public RedirectView create(User user) {
        parameterService.setUniqueId(user);
    	return super.create(user);
    }

    @RequestMapping(value = "edit.do", method = { RequestMethod.POST })
    public RedirectView edit(User user) {
    	return super.edit(user);
    }

    @RequestMapping(value = "delete.do")
    public RedirectView delete(User user) {
    	return super.delete(user);
    }

    @RequestMapping(value="login.do")
    public ModelAndView toLogin(){
    	return new ModelAndView("system/user/login");
    }
    
    @RequestMapping(value="to_register.do")
    public ModelAndView toRegister(){
    	return new ModelAndView("system/user/register");
    }
    
    @RequestMapping(value = "register.do", method = {RequestMethod.POST})
    public RedirectView doRegister(HttpServletRequest request, User user){
    	RedirectView rv = new RedirectView();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword());
//        PasswordEncoder passwordEncoder = new MessageDigestPasswordEncoder("MD5");
        user.setPassword(new MessageDigestPasswordEncoder("MD5").encodePassword(user.getPassword(), user.getName()));
        user.setCreateTime(TimeTools.now());
        parameterService.setUniqueId(user);
    	service.create(user);
        try{
	        token.setDetails(new WebAuthenticationDetails(request));
	        Authentication authenticatedUser = authenticationManager.authenticate(token);
	        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
	        request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
	        rv.setUrl("index.do");
        }catch( AuthenticationException e ){
        	e.printStackTrace();
            rv.setUrl("register?error=true");
        }
        return rv;
    }
    
    @RequestMapping(value = "auth_manage.do")
    public ModelAndView authManage(Long userId){
    	ModelAndView mv = new ModelAndView(this.getJspBasePath()+"auth_manage");
    	List<Role> roles = roleService.getAll(Role.class);
    	mv.addObject("roles", roles);
    	mv.addObject("userId", userId);
    	return mv;
    }
    
    @RequestMapping(value = "role_manage.do")
    public ModelAndView userRoleManage(User user){
    	ModelAndView mv = new ModelAndView("system/user/role_manager");
    	List<Role> list = service.getRolesByUser(user);
    	mv.addObject("roles", list);
    	mv.addObject("user", user);
    	return mv;
    }

}
