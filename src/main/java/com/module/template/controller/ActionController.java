package com.module.template.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.base.common.controller.AbstractController;
import com.base.common.service.BaseService;
import com.module.template.model.Action;
import com.module.template.service.IActionService;
import com.module.system.service.ParameterService;

/**
 * 属性模块
 * author wenxy
**/
@Controller
@RequestMapping("/action/")
public class ActionController extends AbstractController<Action> {
	
	@Resource(name="actionService")
	private IActionService service;

	@Resource(name="parameterService")
	private ParameterService parameterService;

	@Override
	protected ParameterService getParameterService() {
		return parameterService;
	}

	@Override
	protected BaseService<Action> getService() {
		return service;
	}

	@Override
	protected Class<Action> getClassTemplate() {
		return Action.class;
	}

	@Override
	protected String getJspBasePath() {
		return "template/action/";
	}

	@Override
	protected String getBranchName() {
		return "action";
	}
	
	@RequestMapping(value = "list.ftl")
	public ModelAndView list(Long moduleId){
		Action action = new Action();
		action.setModuleId(moduleId);
		ModelAndView mv = super.list(action);
		mv.addObject("moduleId", moduleId);
		return mv;
	}
	
    @RequestMapping(value = "toCreate.ftl")
    public ModelAndView toCreate(Long moduleId) {
    	ModelAndView mv = super.toCreate();
		mv.addObject("moduleId", moduleId);
		return mv;
    }

    @RequestMapping(value = "toEdit.ftl")
    public ModelAndView toEdit(Long id) {
        ModelAndView mv = new ModelAndView(this.getJspBasePath() + "edit");
        Action object = this.getService().getObject(this.getClassTemplate(),id);
        mv.addObject("object", object);
        return mv;
    }

    @RequestMapping(value = "create.ftl", method = { RequestMethod.POST })
    public RedirectView create(Action action) {
    	super.create(action);
    	return new RedirectView("list.ftl?moduleId="+action.getModuleId());
    }

    @RequestMapping(value = "edit.ftl", method = { RequestMethod.POST })
    public RedirectView edit(Action action) {
    	super.edit(action);
    	return new RedirectView("list.ftl?moduleId="+action.getModuleId());
    }

    @RequestMapping(value = "delete.ftl")
    public RedirectView delete(Action action) {
    	super.delete(action);
    	return new RedirectView("list.ftl?moduleId="+action.getModuleId());
    }
    
}