package com.module.novel.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.base.common.controller.AbstractController;
import com.base.common.service.BaseService;
import com.module.novel.model.Race;
import com.module.novel.service.IRaceService;
import com.module.system.service.ParameterService;

@Controller
@RequestMapping("race")
public class RaceController extends AbstractController<Race> {
	
	@Resource(name="raceService")
	private IRaceService service;

	@Resource(name="parameterService")
	private ParameterService parameterService;

	@Override
	protected ParameterService getParameterService() {
		return parameterService;
	}

	@Override
	protected BaseService<Race> getService() {
		return service;
	}

	@Override
	protected Class<Race> getClassTemplate() {
		return Race.class;
	}

	@Override
	protected String getJspBasePath() {
		return "novel/race/";
	}

	@Override
	protected String getBranchName() {
		return "race";
	}

    protected RedirectView getIndexRedirect(Long novelId) {
		RedirectView rv = super.getIndexRedirect();
		rv.addStaticAttribute("novelId", novelId);
		return rv;
	}
	
	@RequestMapping(value = "list.do")
	public ModelAndView list(Race race){
		return super.list(race);
	}
	
    @RequestMapping(value = "toCreate.do")
    public ModelAndView toCreate(Race race) {
    	return super.toCreate(race);
    }

    @RequestMapping(value = "toEdit.do")
    public ModelAndView toEdit(Long id) {
    	return super.toEdit(id);
    }

    @RequestMapping(value = "create.do", method = { RequestMethod.POST })
    public RedirectView create(Race race) {
    	super.create(race);
    	return this.getIndexRedirect(race.getNovelId());
    }

    @RequestMapping(value = "edit.do", method = { RequestMethod.POST })
    public RedirectView edit(Race race) {
    	super.edit(race);
    	return this.getIndexRedirect(race.getNovelId());
    }

    @RequestMapping(value = "delete.do")
    public RedirectView delete(Race race) {
    	super.delete(race);
    	return this.getIndexRedirect(race.getNovelId());
    }

}
