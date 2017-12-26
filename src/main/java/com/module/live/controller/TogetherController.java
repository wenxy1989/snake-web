package com.module.live.controller;

import com.base.common.controller.AbstractController;
import com.base.common.service.BaseService;
import com.module.live.model.Together;
import com.module.live.service.ITogetherService;
import com.module.system.service.ParameterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;

/**
 * 房屋合租模块
 * author wenxy
**/
@Controller
@RequestMapping("/together/")
public class TogetherController extends AbstractController<Together> {
	
	@Resource(name="togetherService")
	private ITogetherService service;

	@Resource(name="parameterService")
	private ParameterService parameterService;

	@Override
	protected ParameterService getParameterService() {
		return parameterService;
	}

	@Override
	protected BaseService<Together> getService() {
		return service;
	}

	@Override
	protected Class<Together> getClassTemplate() {
		return Together.class;
	}

	@Override
	protected String getJspBasePath() {
		return "live/together/";
	}

	@Override
	protected String getBranchName() {
		return "together";
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
        Together object = this.getService().getObject(this.getClassTemplate(),id);
        mv.addObject("object", object);
        return mv;
    }

    @RequestMapping(value = "create.ftl", method = { RequestMethod.POST })
    public RedirectView create(Together together) {
    	super.create(together);
    	return new RedirectView("list.ftl");
    }

    @RequestMapping(value = "edit.ftl", method = { RequestMethod.POST })
    public RedirectView edit(Together together) {
    	super.edit(together);
    	return new RedirectView("list.ftl");
    }

    @RequestMapping(value = "delete.ftl")
    public RedirectView delete(Together together) {
    	super.delete(together);
    	return new RedirectView("list.ftl");
    }
    
}