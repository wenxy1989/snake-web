package com.module.novel.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.base.common.controller.AbstractController;
import com.base.common.service.BaseService;
import com.module.novel.model.Energy;
import com.module.novel.service.IEnergyService;
import com.module.system.service.ParameterService;

@Controller
@RequestMapping("/energy/")
public class EnergyController extends AbstractController<Energy> {
	
	@Resource(name="energyService")
	private IEnergyService service;

	@Resource(name="parameterService")
	private ParameterService parameterService;

	@Override
	protected ParameterService getParameterService() {
		return parameterService;
	}

	@Override
	protected BaseService<Energy> getService() {
		return service;
	}

	@Override
	protected Class<Energy> getClassTemplate() {
		return Energy.class;
	}

	@Override
	protected String getJspBasePath() {
		return "novel/energy/";
	}

	@Override
	protected String getBranchName() {
		return "energy";
	}
	
	@RequestMapping(value = "list.do")
	public ModelAndView list(Energy energy){
		return super.list(energy);
	}
	
    @RequestMapping(value = "toCreate.do")
    public ModelAndView toCreate(Energy energy) {
    	return super.toCreate(energy);
    }

    @RequestMapping(value = "toEdit.do")
    public ModelAndView toEdit(Long id) {
    	return super.toEdit(id);
    }

    @RequestMapping(value = "create.do", method = { RequestMethod.POST })
    public RedirectView create(Energy energy) {
    	return super.create(energy);
    }

    @RequestMapping(value = "edit.do", method = { RequestMethod.POST })
    public RedirectView edit(Energy energy) {
    	return super.edit(energy);
    }

    @RequestMapping(value = "delete.do")
    public RedirectView delete(Energy energy) {
    	return super.delete(energy);
    }

}
