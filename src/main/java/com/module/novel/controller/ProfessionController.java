package com.module.novel.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.base.common.controller.AbstractController;
import com.base.common.service.BaseService;
import com.module.novel.model.Profession;
import com.module.novel.service.IProfessionService;
import com.module.system.service.ParameterService;

@Controller
@RequestMapping("/profession/")
public class ProfessionController extends AbstractController<Profession> {
	
	@Resource(name="professionService")
	private IProfessionService service;

	@Resource(name="parameterService")
	private ParameterService parameterService;

	@Override
	protected ParameterService getParameterService() {
		return parameterService;
	}

	@Override
	protected BaseService<Profession> getService() {
		return service;
	}

	@Override
	protected Class<Profession> getClassTemplate() {
		return Profession.class;
	}

	@Override
	protected String getJspBasePath() {
		return "novel/profession/";
	}

	@Override
	protected String getBranchName() {
		return "profession";
	}

    protected RedirectView getIndexRedirect(Long novelId) {
		RedirectView rv = super.getIndexRedirect();
		rv.addStaticAttribute("novelId", novelId);
		return rv;
	}
	
	@RequestMapping(value = "list.do")
	public ModelAndView list(Profession profession){
		return super.list(profession);
	}
	
    @RequestMapping(value = "toCreate.do")
    public ModelAndView toCreate(Profession profession) {
    	return super.toCreate(profession);
    }

    @RequestMapping(value = "toEdit.do")
    public ModelAndView toEdit(Long id) {
    	return super.toEdit(id);
    }

    @RequestMapping(value = "create.do", method = { RequestMethod.POST })
    public RedirectView create(Profession profession) {
    	super.create(profession);
    	return this.getIndexRedirect(profession.getNovelId());
    }

    @RequestMapping(value = "edit.do", method = { RequestMethod.POST })
    public RedirectView edit(Profession profession) {
    	super.edit(profession);
    	return this.getIndexRedirect(profession.getNovelId());
    }

    @RequestMapping(value = "delete.do")
    public RedirectView delete(Profession profession) {
    	super.delete(profession);
    	return this.getIndexRedirect(profession.getNovelId());
    }

}
