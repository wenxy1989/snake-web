package com.module.novel.controller;

import com.base.common.controller.AbstractController;
import com.module.novel.model.Character;
import com.module.novel.service.ICharacterService;
import com.module.system.service.ParameterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;

@Controller
@RequestMapping("/character/")
public class CharacterController extends AbstractController<Character> {
	
	@Resource(name="characterService")
	private ICharacterService service;

	@Resource(name="parameterService")
	private ParameterService parameterService;

	@Override
	protected ParameterService getParameterService() {
		return parameterService;
	}
	
	@Override
	protected ICharacterService getService() {
		return service;
	}

	@Override
	protected Class<Character> getClassTemplate() {
		return Character.class;
	}

	@Override
	protected String getJspBasePath() {
		return "novel/character/";
	}

	@Override
	protected String getBranchName() {
		return "character";
	}

    protected RedirectView getIndexRedirect(Character character) {
		RedirectView rv = super.getIndexRedirect();
//		rv.addStaticAttribute("belongId", belongId);
		return rv;
	}
	
	@RequestMapping(value = "list.do")
	public ModelAndView list(Character character){
		return super.list(character);
	}
	
    @RequestMapping(value = "toCreate.do")
    public ModelAndView toCreate(Character character) {
    	return super.toCreate(character);
    }

    @RequestMapping(value = "toEdit.do")
    public ModelAndView toEdit(Long id) {
    	return super.toEdit(id);
    }

    @RequestMapping(value = "create.do", method = { RequestMethod.POST })
    public RedirectView create(Character character) {
    	super.create(character);
    	return this.getIndexRedirect(character);
    }

    @RequestMapping(value = "edit.do", method = { RequestMethod.POST })
    public RedirectView edit(Character character) {
    	super.edit(character);
    	return this.getIndexRedirect(character);
    }

    @RequestMapping(value = "delete.do")
    public RedirectView delete(Character character) {
    	super.delete(character);
    	return this.getIndexRedirect(character);
    }

}
