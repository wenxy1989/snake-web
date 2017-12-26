package com.module.system.controller;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.base.common.controller.AbstractController;
import com.base.common.service.BaseService;
import com.module.system.model.Menu;
import com.module.system.service.IMenuService;
import com.module.system.service.IParameterService;
import com.module.system.model.Role;
import com.module.system.service.IRoleService;

@Controller
@RequestMapping("/role/")
public class RoleController extends AbstractController<Role> {
	
	@Resource(name="menuService")
	private IMenuService menuService;
	
	@Resource(name="roleService")
	private IRoleService service;

	@Resource(name="parameterService")
	private IParameterService parameterService;

	@Override
	protected IParameterService getParameterService() {
		return parameterService;
	}

	@Override
	protected BaseService<Role> getService() {
		return service;
	}

	@Override
	protected Class<Role> getClassTemplate() {
		return Role.class;
	}

	@Override
	protected String getJspBasePath() {
		return "system/role/";
	}

	@Override
	protected String getBranchName() {
		return "role";
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
    public ModelAndView toEdit(String code) {
        ModelAndView mv = new ModelAndView(this.getJspBasePath() + "edit");
        Role object = this.getService().getObject(this.getClassTemplate(),code);
        mv.addObject("object", object);
        return mv;
    }

    @RequestMapping(value = "create.do", method = { RequestMethod.POST })
    public RedirectView create(Role role) {
    	return super.create(role);
    }

    @RequestMapping(value = "edit.do", method = { RequestMethod.POST })
    public RedirectView edit(Role role) {
    	return super.edit(role);
    }

    @RequestMapping(value = "delete.do")
    public RedirectView delete(Role role) {
    	return super.delete(role);
    }

    @RequestMapping(value = "access_manage.do")
    public ModelAndView accessManage(Long roleId) {
    	ModelAndView mv = new ModelAndView(this.getJspBasePath()+"access_manage");
    	List<Menu> menus = menuService.getAll(Menu.class);
    	mv.addObject("menus",menus);
    	mv.addObject("roleId",roleId);
    	return mv;
    }

    @ResponseBody
    @RequestMapping(value = "ajax_list_by_userid.do")
    public Object getRolesByUserId(Long userId){
    	return service.getListByUserId(userId);
    }
    
}