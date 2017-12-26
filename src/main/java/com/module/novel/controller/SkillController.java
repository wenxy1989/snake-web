package com.module.novel.controller;


import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.base.common.controller.AbstractController;
import com.base.common.service.BaseService;
import com.module.novel.model.Skill;
import com.module.novel.service.ISkillService;
import com.module.system.service.ParameterService;

@Controller
@RequestMapping("/skill/")
public class SkillController extends AbstractController<Skill> {
	
	@Resource(name="skillService")
	private ISkillService service;

	@Resource(name="parameterService")
	private ParameterService parameterService;

	@Override
	protected ParameterService getParameterService() {
		return parameterService;
	}

	@Override
	protected BaseService<Skill> getService() {
		return service;
	}

	@Override
	protected Class<Skill> getClassTemplate() {
		return Skill.class;
	}

	@Override
	protected String getJspBasePath() {
		return "novel/skill/";
	}

	@Override
	protected String getBranchName() {
		return "skill";
	}
	
	@RequestMapping(value = "list.do")
	public ModelAndView list(Skill skill){
		return super.list(skill);
	}
	
    @RequestMapping(value = "toCreate.do")
    public ModelAndView toCreate(Skill skill) {
    	return super.toCreate(skill);
    }

    @RequestMapping(value = "toEdit.do")
    public ModelAndView toEdit(Long id) {
    	return super.toEdit(id);
    }

    @RequestMapping(value = "create.do", method = { RequestMethod.POST })
    public RedirectView create(Skill skill) {
    	RedirectView rv = super.create(skill);
    	rv.addStaticAttribute("novelId", skill.getNovelId());
    	return rv;
    }

    @RequestMapping(value = "edit.do", method = { RequestMethod.POST })
    public RedirectView edit(Skill skill) {
    	RedirectView rv = super.edit(skill);
    	rv.addStaticAttribute("novelId", skill.getNovelId());
    	return rv;
    }

    @RequestMapping(value = "delete.do")
    public RedirectView delete(Skill skill) {
    	RedirectView rv = super.delete(skill);
    	rv.addStaticAttribute("novelId", skill.getNovelId());
    	return rv;
    }

}
