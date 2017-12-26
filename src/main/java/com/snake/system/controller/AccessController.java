package com.snake.system.controller;

import com.base.common.service.BaseService;
import com.snake.system.model.Access;
import com.snake.system.service.IAccessService;
import com.snake.system.service.ParameterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/access/")
public class AccessController extends AbstractController<Access> {
	
	@Resource(name="accessService")
	private IAccessService service;

	@Resource(name="parameterService")
	private ParameterService parameterService;

	@Override
	protected ParameterService getParameterService() {
		return parameterService;
	}

	@Override
	protected BaseService<Access> getService() {
		return service;
	}

	@Override
	protected Class<Access> getClassTemplate() {
		return Access.class;
	}

	@Override
	protected String getJspBasePath() {
		return "system/access/";
	}

	@Override
	protected String getBranchName() {
		return "access";
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
    public RedirectView create(Access access) {
    	return super.create(access);
    }

    @RequestMapping(value = "edit.do", method = { RequestMethod.POST })
    public RedirectView edit(Access access) {
    	return super.edit(access);
    }

    @RequestMapping(value = "delete.do")
    public RedirectView delete(Access access) {
    	return super.delete(access);
    }

    @ResponseBody
    @RequestMapping(value = "ajax_list_by_roleid.do",method = {RequestMethod.POST})
    public Object ajaxListByRoleId(Long roleId) {
    	List<Access> access = service.getListByRoleId(roleId);
    	return access;
    }

    @ResponseBody
    @RequestMapping(value = "ajax_add.do",method = {RequestMethod.POST})
    public Object ajaxAdd(Access access) {
    	super.create(access);
    	return RESULT_SUCCESS;
    }

    @ResponseBody
    @RequestMapping(value = "ajax_remove.do",method = {RequestMethod.POST})
    public Object ajaxRemove(Access access) {
    	service.deleteByRoleIdAndMenuId(access.getRoleId(),access.getMenuId());
    	return RESULT_SUCCESS;
    }
}