package com.module.template.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.base.common.controller.AbstractController;
import com.base.common.service.BaseService;
import com.module.template.model.Attribute;
import com.module.template.service.IAttributeService;
import com.module.system.service.ParameterService;

/**
 * 属性模块
 * author wenxy
**/
@Controller
@RequestMapping("/attribute/")
public class AttributeController extends AbstractController<Attribute> {
	
	@Resource(name="attributeService")
	private IAttributeService service;

	@Resource(name="parameterService")
	private ParameterService parameterService;

	@Override
	protected ParameterService getParameterService() {
		return parameterService;
	}

	@Override
	protected BaseService<Attribute> getService() {
		return service;
	}

	@Override
	protected Class<Attribute> getClassTemplate() {
		return Attribute.class;
	}

	@Override
	protected String getJspBasePath() {
		return "template/attribute/";
	}

	@Override
	protected String getBranchName() {
		return "attribute";
	}
	
	@RequestMapping(value = "list.ftl")
	public ModelAndView list(Long moduleId){
		Attribute attribute = new Attribute();
		attribute.setModuleId(moduleId);
		ModelAndView mv = super.list(attribute);
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
        Attribute object = this.getService().getObject(this.getClassTemplate(),id);
        mv.addObject("object", object);
        return mv;
    }

    @RequestMapping(value = "create.ftl", method = { RequestMethod.POST })
    public RedirectView create(Attribute attribute) {
    	super.create(attribute);
    	return new RedirectView("list.ftl?moduleId="+attribute.getModuleId());
    }

    @RequestMapping(value = "edit.ftl", method = { RequestMethod.POST })
    public RedirectView edit(Attribute attribute) {
    	super.edit(attribute);
    	return new RedirectView("list.ftl?moduleId="+attribute.getModuleId());
    }

    @RequestMapping(value = "delete.ftl")
    public RedirectView delete(Attribute attribute) {
    	super.delete(attribute);
    	return new RedirectView("list.ftl?moduleId="+attribute.getModuleId());
    }
    
}