package com.snake.novel.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.snake.system.controller.AbstractController;
import com.base.common.service.BaseService;
import com.snake.novel.model.Place;
import com.snake.novel.service.IPlaceService;
import com.snake.system.service.ParameterService;

@Controller
@RequestMapping("/place/")
public class PlaceController extends AbstractController<Place> {
	
	@Resource(name="placeService")
	private IPlaceService service;

	@Resource(name="parameterService")
	private ParameterService parameterService;

	@Override
	protected ParameterService getParameterService() {
		return parameterService;
	}

	@Override
	protected BaseService<Place> getService() {
		return service;
	}

	@Override
	protected Class<Place> getClassTemplate() {
		return Place.class;
	}

	@Override
	protected String getJspBasePath() {
		return "novel/place/";
	}

	@Override
	protected String getBranchName() {
		return "place";
	}

    protected RedirectView getIndexRedirect(Long novelId) {
		RedirectView rv = super.getIndexRedirect();
		rv.addStaticAttribute("novelId", novelId);
		return rv;
	}
	
	@RequestMapping(value = "list.do")
	public ModelAndView list(Place place){
		return super.list(place);
	}
	
    @RequestMapping(value = "toCreate.do")
    public ModelAndView toCreate(Place place) {
    	return super.toCreate(place);
    }

    @RequestMapping(value = "toEdit.do")
    public ModelAndView toEdit(Long id) {
    	return super.toEdit(id);
    }

    @RequestMapping(value = "create.do", method = { RequestMethod.POST })
    public RedirectView create(Place place) {
    	super.create(place);
    	return this.getIndexRedirect(place.getNovelId());
    }

    @RequestMapping(value = "edit.do", method = { RequestMethod.POST })
    public RedirectView edit(Place place) {
    	super.edit(place);
    	return this.getIndexRedirect(place.getNovelId());
    }

    @RequestMapping(value = "delete.do")
    public RedirectView delete(Place place) {
    	super.delete(place);
    	return this.getIndexRedirect(place.getNovelId());
    }

}
