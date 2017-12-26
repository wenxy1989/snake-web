package com.snake.novel.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.snake.system.controller.AbstractController;
import com.base.common.service.BaseService;
import com.snake.novel.model.Family;
import com.snake.novel.service.IFamilyService;
import com.snake.system.service.ParameterService;

@Controller
@RequestMapping("/family/")
public class FamilyController extends AbstractController<Family> {
	
	@Resource(name="familyService")
	private IFamilyService service;

	@Resource(name="parameterService")
	private ParameterService parameterService;

	@Override
	protected ParameterService getParameterService() {
		return parameterService;
	}

	@Override
	protected BaseService<Family> getService() {
		return service;
	}

	@Override
	protected Class<Family> getClassTemplate() {
		return Family.class;
	}

	@Override
	protected String getJspBasePath() {
		return "novel/family/";
	}

	@Override
	protected String getBranchName() {
		return "family";
	}
	
	@RequestMapping(value = "list.do")
	public ModelAndView list(Family family){
		return super.list(family);
	}
	
    @RequestMapping(value = "toCreate.do")
    public ModelAndView toCreate(Family family) {
    	return super.toCreate(family);
    }

    @RequestMapping(value = "toEdit.do")
    public ModelAndView toEdit(Long id) {
    	return super.toEdit(id);
    }

    @RequestMapping(value = "create.do", method = { RequestMethod.POST })
    public RedirectView create(Family family) {
    	return super.create(family);
    }

    @RequestMapping(value = "edit.do", method = { RequestMethod.POST })
    public RedirectView edit(Family family) {
    	return super.edit(family);
    }

    @RequestMapping(value = "delete.do")
    public RedirectView delete(Family family) {
    	return super.delete(family);
    }

}
