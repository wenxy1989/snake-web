package com.module.config.controller;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.base.common.controller.AbstractController;
import com.base.common.service.BaseService;
import com.module.config.model.Attr;
import com.module.config.service.IAttrService;
import com.module.config.service.IObjService;
import com.module.config.model.Obj;
import com.module.system.service.ParameterService;

/**
 * 房屋信息模块
 * author wenxy
**/
@Controller
@RequestMapping("/attr/")
public class AttrController extends AbstractController<Attr> {
	
	@Resource(name="attrService")
	private IAttrService service;
	
	@Resource(name="objService")
	private IObjService objService;

	@Resource(name="parameterService")
	private ParameterService parameterService;

	@Override
	protected ParameterService getParameterService() {
		return parameterService;
	}

	@Override
	protected BaseService<Attr> getService() {
		return service;
	}

	@Override
	protected Class<Attr> getClassTemplate() {
		return Attr.class;
	}

	@Override
	protected String getJspBasePath() {
		return "config/attr/";
	}

	@Override
	protected String getBranchName() {
		return "attr";
	}
	
	@RequestMapping(value = "list.do")
	public ModelAndView list(Long objId){
		ModelAndView mv = new ModelAndView(this.getJspBasePath() + "list");
		Obj obj = objService.getObject(Obj.class, objId);
		mv.addObject("obj", obj);
		List<Attr> list = service.getListByObjectId(objId);
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
        Attr object = this.getService().getObject(this.getClassTemplate(),id);
        mv.addObject("object", object);
        return mv;
    }

    @RequestMapping(value = "create.do", method = { RequestMethod.POST })
    public RedirectView create(Attr attr) {
    	super.create(attr);
    	return new RedirectView("list.do?objId="+attr.getObjId());
    }

    @RequestMapping(value = "edit.do", method = { RequestMethod.POST })
    public RedirectView edit(Attr attr) {
    	super.edit(attr);
    	return new RedirectView("list.do?objId="+attr.getObjId());
    }

    @RequestMapping(value = "delete.do")
    public RedirectView delete(Attr attr) {
    	attr = service.getObject(Attr.class, attr.getId());
    	super.delete(attr);
    	return new RedirectView("list.do?objId="+attr.getObjId());
    }
    
    
}