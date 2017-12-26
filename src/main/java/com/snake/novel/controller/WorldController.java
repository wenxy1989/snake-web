package com.snake.novel.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.snake.system.controller.AbstractController;
import com.base.common.service.BaseService;
import com.snake.novel.model.World;
import com.snake.novel.service.IWorldService;
import com.snake.system.service.ParameterService;

@Controller
@RequestMapping("/world/")
public class WorldController extends AbstractController<World> {
	
	@Resource(name="worldService")
	private IWorldService service;

	@Resource(name="parameterService")
	private ParameterService parameterService;

	@Override
	protected ParameterService getParameterService() {
		return parameterService;
	}

	@Override
	protected BaseService<World> getService() {
		return service;
	}

	@Override
	protected Class<World> getClassTemplate() {
		return World.class;
	}

	@Override
	protected String getJspBasePath() {
		return "novel/world/";
	}

	@Override
	protected String getBranchName() {
		return "world";
	}

    protected RedirectView getIndexRedirect(Long novelId) {
		RedirectView rv = super.getIndexRedirect();
		rv.addStaticAttribute("novelId", novelId);
		return rv;
	}
	
	@RequestMapping(value = "list.do")
	public ModelAndView list(World world){
		return super.list(world);
	}
	
    @RequestMapping(value = "toCreate.do")
    public ModelAndView toCreate(World world) {
    	return super.toCreate(world);
    }

    @RequestMapping(value = "toEdit.do")
    public ModelAndView toEdit(Long id) {
    	return super.toEdit(id);
    }

    @RequestMapping(value = "create.do", method = { RequestMethod.POST })
    public RedirectView create(World world) {
    	super.create(world);
    	return this.getIndexRedirect(world.getNovelId());
    }

	@RequestMapping(value = "edit.do", method = { RequestMethod.POST })
    public RedirectView edit(World world) {
    	super.edit(world);
    	return this.getIndexRedirect(world.getNovelId());
    }

    @RequestMapping(value = "delete.do")
    public RedirectView delete(World world) {
    	super.delete(world);
    	return this.getIndexRedirect(world.getNovelId());
    }

    @RequestMapping(value = "details.do")
    public ModelAndView details(Long id){
    	ModelAndView mv = new ModelAndView(getJspBasePath()+"details");
    	return mv;
    }

}
