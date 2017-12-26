package com.snake.novel.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.snake.system.controller.AbstractController;
import com.base.common.service.BaseService;
import com.snake.novel.model.Equipment;
import com.snake.novel.service.IEquipmentService;
import com.snake.system.service.ParameterService;

@Controller
@RequestMapping("/equipment/")
public class EquipmentController extends AbstractController<Equipment> {
	
	@Resource(name="equipmentService")
	private IEquipmentService service;

	@Resource(name="parameterService")
	private ParameterService parameterService;

	@Override
	protected ParameterService getParameterService() {
		return parameterService;
	}

	@Override
	protected BaseService<Equipment> getService() {
		return service;
	}

	@Override
	protected Class<Equipment> getClassTemplate() {
		return Equipment.class;
	}

	@Override
	protected String getJspBasePath() {
		return "novel/equipment/";
	}

	@Override
	protected String getBranchName() {
		return "equipment";
	}
	
	@RequestMapping(value = "list.do")
	public ModelAndView list(Equipment equipment){
		return super.list(equipment);
	}
	
    @RequestMapping(value = "toCreate.do")
    public ModelAndView toCreate(Equipment equipment) {
    	return super.toCreate(equipment);
    }

    @RequestMapping(value = "toEdit.do")
    public ModelAndView toEdit(Long id) {
    	return super.toEdit(id);
    }

    @RequestMapping(value = "create.do", method = { RequestMethod.POST })
    public RedirectView create(Equipment equipment) {
    	return super.create(equipment);
    }

    @RequestMapping(value = "edit.do", method = { RequestMethod.POST })
    public RedirectView edit(Equipment equipment) {
    	return super.edit(equipment);
    }

    @RequestMapping(value = "delete.do")
    public RedirectView delete(Equipment equipment) {
    	return super.delete(equipment);
    }

}
