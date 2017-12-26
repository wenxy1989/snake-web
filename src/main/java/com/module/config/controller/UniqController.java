package com.module.config.controller;

import com.base.common.controller.AbstractController;
import com.base.common.service.BaseService;
import com.module.config.model.Obj;
import com.module.config.model.Uniq;
import com.module.system.service.ParameterService;
import com.module.config.service.IObjService;
import com.module.config.service.IUniqService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import java.util.List;

/**
 * 房屋信息模块
 * author wenxy
**/
@Controller
@RequestMapping("/uniq/")
public class UniqController extends AbstractController<Uniq> {
	
	@Resource(name="uniqService")
	private IUniqService service;
	
	@Resource(name="objService")
	private IObjService objService;

	@Resource(name="parameterService")
	private ParameterService parameterService;

	@Override
	protected ParameterService getParameterService() {
		return parameterService;
	}

	@Override
	protected BaseService<Uniq> getService() {
		return service;
	}

	@Override
	protected Class<Uniq> getClassTemplate() {
		return Uniq.class;
	}

	@Override
	protected String getJspBasePath() {
		return "config/uniq/";
	}

	@Override
	protected String getBranchName() {
		return "uniq";
	}
	
	@RequestMapping(value = "list.do")
	public ModelAndView list(Long objId){
		ModelAndView mv = new ModelAndView(this.getJspBasePath() + "list");
		Obj obj = objService.getObject(Obj.class, objId);
		mv.addObject("obj", obj);
		List<Uniq> list = this.getService().getAll(this.getClassTemplate());
		mv.addObject("objectList", list);
		return mv;
	}
	
    @RequestMapping(value = "toCreate.do")
    public ModelAndView toCreate(Long objId) {
    	ModelAndView mv = super.toCreate();
		Obj obj = objService.getObject(Obj.class, objId);
		mv.addObject("obj",obj);
		return mv;
    }

    @RequestMapping(value = "toEdit.do")
    public ModelAndView toEdit(Long id) {
        ModelAndView mv = new ModelAndView(this.getJspBasePath() + "edit");
        Uniq object = this.getService().getObject(this.getClassTemplate(),id);
        mv.addObject("object", object);
        return mv;
    }

    @RequestMapping(value = "create.do", method = { RequestMethod.POST })
    public RedirectView create(Uniq uniq) {
    	super.create(uniq);
    	return new RedirectView("list.do?objId="+uniq.getObjId());
    }

    @RequestMapping(value = "edit.do", method = { RequestMethod.POST })
    public RedirectView edit(Uniq uniq) {
    	super.edit(uniq);
    	return new RedirectView("list.do?objId="+uniq.getObjId());
    }

    @RequestMapping(value = "delete.do")
    public RedirectView delete(Uniq uniq) {
    	uniq = service.getObject(Uniq.class, uniq.getId());
    	super.delete(uniq);
    	return new RedirectView("list.do?objId="+uniq.getObjId());
    }
    
    
}