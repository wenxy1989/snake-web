package com.model.${moduleCode}.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.base.common.controller.AbstractController;
import com.base.common.service.BaseService;
import com.model.${moduleCode}.${className};
import com.model.${moduleCode}.service.I${className}Service;
import com.model.system.parameter.service.ParameterService;

/**
 * ${moduleName }
 * author ${author }
**/
@Controller
@RequestMapping("/${moduleCode}/")
public class ${className}Controller extends AbstractController<${className }> {
	
	@Resource(name="${moduleCode}Service")
	private I${className}Service service;

	@Resource(name="parameterService")
	private ParameterService parameterService;

	@Override
	protected ParameterService getParameterService() {
		return parameterService;
	}

	@Override
	protected BaseService<${className}> getService() {
		return service;
	}

	@Override
	protected Class<${className}> getClassTemplate() {
		return ${className}.class;
	}

	@Override
	protected String getJspBasePath() {
		return "${moduleCode}/";
	}

	@Override
	protected String getBranchName() {
		return "${moduleCode}";
	}
	
	@RequestMapping(value = "list.ftl")
	public ModelAndView list(){
		return super.list();
	}
	
    @RequestMapping(value = "toCreate.ftl")
    public ModelAndView toCreate() {
    	return super.toCreate();
    }

    @RequestMapping(value = "toEdit.ftl")
    public ModelAndView toEdit(Long id) {
        ModelAndView mv = new ModelAndView(this.getJspBasePath() + "edit");
        ${className} object = this.getService().getObject(this.getClassTemplate(),id);
        mv.addObject("object", object);
        return mv;
    }

    @RequestMapping(value = "create.ftl", method = { RequestMethod.POST })
    public RedirectView create(${className} ${moduleCode}) {
    	super.create(${moduleCode});
    	return new RedirectView("list.ftl");
    }

    @RequestMapping(value = "edit.ftl", method = { RequestMethod.POST })
    public RedirectView edit(${className} ${moduleCode}) {
    	super.edit(${moduleCode});
    	return new RedirectView("list.ftl");
    }

    @RequestMapping(value = "delete.ftl")
    public RedirectView delete(${className} ${moduleCode}) {
    	super.delete(${moduleCode});
    	return new RedirectView("list.ftl");
    }
    
    <#if actions?exists>
	<#list actions as action>
	/**
	 *${action.name }
	 */
    @RequestMapping(value = "${action.code?lower_case}.ftl")
    public ${action.returnType} ${action.code}(${action.paramTypes}) {
    <#if action.actionSql?exists>
    	return service.${action.code}(${action.paramNames});
    </#if>
    }
    </#list>
    </#if>
    
}