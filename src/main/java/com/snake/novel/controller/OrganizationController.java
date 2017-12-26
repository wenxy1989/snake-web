package com.snake.novel.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.snake.system.controller.AbstractController;
import com.base.common.service.BaseService;
import com.snake.novel.model.Organization;
import com.snake.novel.service.IOrganizationService;
import com.snake.system.service.ParameterService;

@Controller
@RequestMapping("/organization/")
public class OrganizationController extends AbstractController<Organization> {
	
	@Resource(name="organizationService")
	private IOrganizationService service;

	@Resource(name="parameterService")
	private ParameterService parameterService;

	@Override
	protected ParameterService getParameterService() {
		return parameterService;
	}

	@Override
	protected BaseService<Organization> getService() {
		return service;
	}

	@Override
	protected Class<Organization> getClassTemplate() {
		return Organization.class;
	}

	@Override
	protected String getJspBasePath() {
		return "novel/organization/";
	}

	@Override
	protected String getBranchName() {
		return "organization";
	}
	
	@RequestMapping(value = "list.do")
	public ModelAndView list(Organization organization){
		return super.list(organization);
	}
	
    @RequestMapping(value = "toCreate.do")
    public ModelAndView toCreate(Organization organization) {
    	return super.toCreate(organization);
    }

    @RequestMapping(value = "toEdit.do")
    public ModelAndView toEdit(Long id) {
    	return super.toEdit(id);
    }

    @RequestMapping(value = "create.do", method = { RequestMethod.POST })
    public RedirectView create(Organization organization) {
    	return super.create(organization);
    }

    @RequestMapping(value = "edit.do", method = { RequestMethod.POST })
    public RedirectView edit(Organization organization) {
    	return super.edit(organization);
    }

    @RequestMapping(value = "delete.do")
    public RedirectView delete(Organization organization) {
    	return super.delete(organization);
    }

}
