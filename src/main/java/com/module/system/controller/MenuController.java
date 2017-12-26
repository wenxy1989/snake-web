package com.module.system.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.base.common.controller.AbstractController;
import com.base.common.service.BaseService;
import com.module.system.service.ParameterService;
import com.module.system.service.IMenuService;
import com.module.system.model.Menu;

@Controller
@RequestMapping("/menu/")
public class MenuController extends AbstractController<Menu> {
	
	@Resource(name="menuService")
	private IMenuService service;

	@Resource(name="parameterService")
	private ParameterService parameterService;

	@Override
	protected ParameterService getParameterService() {
		return parameterService;
	}

	@Override
	protected BaseService<Menu> getService() {
		return service;
	}

	@Override
	protected Class<Menu> getClassTemplate() {
		return Menu.class;
	}

	@Override
	protected String getJspBasePath() {
		return "system/menu/";
	}

	@Override
	protected String getBranchName() {
		return "menu";
	}
	
    @RequestMapping(value="list.do",method = {RequestMethod.GET})
    public ModelAndView list(Menu menu) {
        return super.list(menu);
    }
	
    @RequestMapping(value="toCreate.do")
    public ModelAndView toCreate(Menu object) {
        ModelAndView mv = super.toCreate();
        mv.addObject("object", object);
        return mv;
    }

    @RequestMapping(value = "toEdit.do")
    public ModelAndView toEdit(Long id) {
    	return super.toEdit(id);
    }

    @RequestMapping(value = "create.do", method = { RequestMethod.POST })
    public RedirectView create(Menu menu) {
    	return super.create(menu);
    }

    @RequestMapping(value = "edit.do", method = { RequestMethod.POST })
    public RedirectView edit(Menu menu) {
    	return super.edit(menu);
    }

    @RequestMapping(value = "delete.do")
    public RedirectView delete(Menu menu) {
    	return super.delete(menu);
    }

    @RequestMapping(value = "manage.do")
    public ModelAndView manage() {
    	return new ModelAndView(this.getJspBasePath()+"/manager");
    }

    @ResponseBody
    @RequestMapping(value = "load_all.do")
    public Object loadAll(){
    	return service.getAll(Menu.class);
    }

    @RequestMapping(value = "details.do")
    public Object details(Long id){
    	ModelAndView mv = new ModelAndView(this.getJspBasePath()+"/details");
    	Menu object = service.getObject(Menu.class, id);
    	mv.addObject("object", object);
    	return mv;
    }
    
}