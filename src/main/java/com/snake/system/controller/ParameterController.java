package com.snake.system.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.base.common.service.BaseService;
import com.snake.system.service.IParameterService;
import com.snake.system.service.ParameterService;
import com.snake.system.model.Parameter;

@Controller
@RequestMapping("/parameter/")
public class ParameterController extends AbstractController<Parameter> {
	
	@Resource(name="parameterService")
	private IParameterService service;

	@Resource(name="parameterService")
	private ParameterService parameterService;

	@Override
	protected ParameterService getParameterService() {
		return parameterService;
	}

	@Override
	protected BaseService<Parameter> getService() {
		return service;
	}

	@Override
	protected Class<Parameter> getClassTemplate() {
		return Parameter.class;
	}

	@Override
	protected String getJspBasePath() {
		return "system/parameter/";
	}

	@Override
	protected String getBranchName() {
		return "parameter";
	}
	
    @RequestMapping(value="list.do",method = {RequestMethod.GET})
    public ModelAndView list(Parameter parameter) {
        return super.list(parameter);
    }
	
    @RequestMapping(value="toCreate.do")
    public ModelAndView toCreate(Parameter parameter) {
        return super.toCreate(parameter);
    }

    @RequestMapping(value = "toEdit.do")
    public ModelAndView toEdit(Long id) {
    	return super.toEdit(id);
    }

    @RequestMapping(value = "create.do", method = { RequestMethod.POST })
    public RedirectView create(Parameter parameter) {
    	return super.create(parameter);
    }

    @RequestMapping(value = "edit.do", method = { RequestMethod.POST })
    public RedirectView edit(Parameter parameter) {
    	return super.edit(parameter);
    }

    @RequestMapping(value = "delete.do")
    public RedirectView delete(Parameter parameter) {
    	return super.delete(parameter);
    }
    
}