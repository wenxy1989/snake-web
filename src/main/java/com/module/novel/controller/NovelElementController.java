package com.module.novel.controller;

import com.base.common.controller.AbstractController;
import com.base.common.service.BaseService;
import com.module.novel.model.NovelElement;
import com.module.novel.service.INovelElementService;
import com.module.system.service.ParameterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;

@Controller
@RequestMapping("/element/")
public class NovelElementController extends AbstractController<NovelElement> {
	
	@Resource(name="elementService")
	private INovelElementService service;

	@Resource(name="parameterService")
	private ParameterService parameterService;

	@Override
	protected BaseService<NovelElement> getService() {
		return service;
	}

	@Override
	protected ParameterService getParameterService() {
		return parameterService;
	}

	@Override
	protected Class<NovelElement> getClassTemplate() {
		return NovelElement.class;
	}

	@Override
	protected String getJspBasePath() {
		return "novel/element/";
	}

	@Override
	protected String getBranchName() {
		return "element";
	}
    
    private RedirectView getIndexRedirect(NovelElement element) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@RequestMapping(value = "list.do")
	public ModelAndView list(NovelElement element){
		ModelAndView mv = super.list(element);
		mv.addObject("element",element);
		return mv;
	}
	
    @RequestMapping(value = "toCreate.do")
    public ModelAndView toCreate(NovelElement element) {
    	ModelAndView mv = super.toCreate(element);
    	return mv;
    }

    @RequestMapping(value = "toEdit.do")
    public ModelAndView toEdit(Long id) {
    	return super.toEdit(id);
    }

    @RequestMapping(value = "create.do", method = { RequestMethod.POST })
    public RedirectView create(NovelElement element) {
    	super.create(element);
    	return this.getIndexRedirect(element);
    }

    @RequestMapping(value = "edit.do", method = { RequestMethod.POST })
    public RedirectView edit(NovelElement element) {
    	super.edit(element);
    	return this.getIndexRedirect(element);
    }

    @RequestMapping(value = "delete.do")
    public RedirectView delete(NovelElement element) {
    	super.delete(element);
    	return this.getIndexRedirect(element);
    }

	@ResponseBody
    @RequestMapping(value = "jsonList.do")
    public Object getJsonList(NovelElement element){
    	return service.getList(element);
    }

}
