package com.module.novel.controller;

import com.base.common.controller.AbstractController;
import com.module.novel.service.INovelElementService;
import com.module.novel.model.Novel;
import com.module.novel.service.INovalService;
import com.module.system.service.ParameterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/novel/")
public class NovalController extends AbstractController<Novel> {
	
	@Resource(name="novelService")
	private INovalService service;

	@Resource(name="parameterService")
	private ParameterService parameterService;
	
	@Resource(name="elementService")
	private INovelElementService elementService;

	@Override
	protected ParameterService getParameterService() {
		return parameterService;
	}

	@Override
	protected INovalService getService() {
		return service;
	}

	@Override
	protected Class<Novel> getClassTemplate() {
		return Novel.class;
	}

	@Override
	protected String getJspBasePath() {
		return "novel/novel/";
	}

	@Override
	protected String getBranchName() {
		return "novel";
	}
	
	@RequestMapping(value = "list.do")
	public ModelAndView list(){
		ModelAndView mv = new ModelAndView(this.getJspBasePath() + "list");
		List<Novel> list = this.getService().getAll(Novel.class);
		if(null != list && list.size() > 0){
			for(Novel novel : list){
				elementService.setNovelElements(novel);
			}
		}
		mv.addObject("list", list);
		return mv;
	}
	
    @RequestMapping(value = "toCreate.do")
    public ModelAndView toCreate() {
    	return super.toCreate();
    }

    @RequestMapping(value = "toEdit.do")
    public ModelAndView toEdit(Long id) {
    	return super.toEdit(id);
    }

    @RequestMapping(value = "create.do", method = { RequestMethod.POST })
    public RedirectView create(Novel novel) {
    	return super.create(novel);
    }

    @RequestMapping(value = "edit.do", method = { RequestMethod.POST })
    public RedirectView edit(Novel novel) {
    	return super.edit(novel);
    }

    @RequestMapping(value = "delete.do")
    public RedirectView delete(Novel novel) {
    	return super.delete(novel);
    }

}
