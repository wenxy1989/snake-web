package com.module.live.controller;

import com.base.common.controller.AbstractController;
import com.base.common.service.BaseService;
import com.module.live.model.House;
import com.module.live.service.IHouseService;
import com.module.system.service.ParameterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;

/**
 * 房屋信息模块
 * author wenxy
**/
@Controller
@RequestMapping("/house/")
public class HouseController extends AbstractController<House> {
	
	@Resource(name="houseService")
	private IHouseService service;

	@Resource(name="parameterService")
	private ParameterService parameterService;

	@Override
	protected ParameterService getParameterService() {
		return parameterService;
	}

	@Override
	protected BaseService<House> getService() {
		return service;
	}

	@Override
	protected Class<House> getClassTemplate() {
		return House.class;
	}

	@Override
	protected String getJspBasePath() {
		return "live/house/";
	}

	@Override
	protected String getBranchName() {
		return "house";
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
        House object = this.getService().getObject(this.getClassTemplate(),id);
        mv.addObject("object", object);
        return mv;
    }

    @RequestMapping(value = "create.ftl", method = { RequestMethod.POST })
    public RedirectView create(House house) {
    	super.create(house);
    	return new RedirectView("list.ftl");
    }

    @RequestMapping(value = "edit.ftl", method = { RequestMethod.POST })
    public RedirectView edit(House house) {
    	super.edit(house);
    	return new RedirectView("list.ftl");
    }

    @RequestMapping(value = "delete.ftl")
    public RedirectView delete(House house) {
    	super.delete(house);
    	return new RedirectView("list.ftl");
    }
    
    
}