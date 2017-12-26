package com.snake.novel.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.snake.system.controller.AbstractController;
import com.base.common.service.BaseService;
import com.snake.novel.model.Peerage;
import com.snake.novel.service.IPeerageService;
import com.snake.system.service.ParameterService;

@Controller
@RequestMapping("/peerage/")
public class PeerageController extends AbstractController<Peerage> {
	
	@Resource(name="peerageService")
	private IPeerageService service;

	@Resource(name="parameterService")
	private ParameterService parameterService;

	@Override
	protected ParameterService getParameterService() {
		return parameterService;
	}

	@Override
	protected BaseService<Peerage> getService() {
		return service;
	}

	@Override
	protected Class<Peerage> getClassTemplate() {
		return Peerage.class;
	}

	@Override
	protected String getJspBasePath() {
		return "novel/peerage/";
	}

	@Override
	protected String getBranchName() {
		return "peerage";
	}
	
	@RequestMapping(value = "list.do")
	public ModelAndView list(Peerage peerage){
		return super.list(peerage);
	}
	
    @RequestMapping(value = "toCreate.do")
    public ModelAndView toCreate(Peerage peerage) {
    	return super.toCreate(peerage);
    }

    @RequestMapping(value = "toEdit.do")
    public ModelAndView toEdit(Long id) {
    	return super.toEdit(id);
    }

    @RequestMapping(value = "create.do", method = { RequestMethod.POST })
    public RedirectView create(Peerage peerage) {
    	return super.create(peerage);
    }

    @RequestMapping(value = "edit.do", method = { RequestMethod.POST })
    public RedirectView edit(Peerage peerage) {
    	return super.edit(peerage);
    }

    @RequestMapping(value = "delete.do")
    public RedirectView delete(Peerage peerage) {
    	return super.delete(peerage);
    }

}
