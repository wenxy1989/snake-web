package com.snake.system.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.base.common.service.BaseService;
import com.snake.system.model.Auth;
import com.snake.system.service.IAuthService;
import com.snake.system.service.ParameterService;
import com.snake.system.service.IRoleService;
import com.snake.system.service.IUserService;

@Controller
@RequestMapping("/auth/")
public class AuthController extends AbstractController<Auth> {
	
	@Resource(name="userService")
	private IUserService userService;
	
	@Resource(name="roleService")
	private IRoleService roleService;
	
	@Resource(name="authService")
	private IAuthService service;

	@Resource(name="parameterService")
	private ParameterService parameterService;

	@Override
	protected ParameterService getParameterService() {
		return parameterService;
	}

	@Override
	protected BaseService<Auth> getService() {
		return service;
	}

	@Override
	protected Class<Auth> getClassTemplate() {
		return Auth.class;
	}

	@Override
	protected String getJspBasePath() {
		return "system/auth/";
	}

	@Override
	protected String getBranchName() {
		return "auth";
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
    public RedirectView create(Auth auth) {
    	return super.create(auth);
    }

    @RequestMapping(value = "edit.do", method = { RequestMethod.POST })
    public RedirectView edit(Auth auth) {
    	return super.edit(auth);
    }

    @RequestMapping(value = "delete.do")
    public RedirectView delete(Auth auth) {
    	return super.delete(auth);
    }

    @ResponseBody
    @RequestMapping(value = "ajax_list_by_userid.do",method = {RequestMethod.POST})
    public Object listByUserId(Long userId) {
    	return service.getListByUserId(userId);
    }

    @ResponseBody
    @RequestMapping(value = "ajax_add.do",method = {RequestMethod.POST})
    public Object ajaxAdd(Auth auth) {
    	super.create(auth);
    	return RESULT_SUCCESS;
    }

    @ResponseBody
    @RequestMapping(value = "ajax_remove.do",method = {RequestMethod.POST})
    public Object ajaxRemove(Auth auth) {
    	service.deleteByUserIdAndRoleId(auth.getUserId(),auth.getRoleId());
    	return RESULT_SUCCESS;
    }
    
}