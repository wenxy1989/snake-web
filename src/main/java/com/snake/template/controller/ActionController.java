package com.snake.template.controller;

import com.snake.system.controller.BasicController;
import com.base.exception.ServiceException;
import com.base.util.Condition;
import com.base.util.Criteria;
import com.base.util.SimpleCriteria;
import com.snake.template.model.Action;
import com.snake.template.service.IActionService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;

/**
 * 属性模块
 * author wenxy
**/
@Controller
@RequestMapping("/action/")
public class ActionController extends BasicController {
	
	@Resource(name="actionService")
	private IActionService service;
	
	@RequestMapping(value = "page")
	public ModelAndView page(Long moduleId){
        ModelAndView mv = new ModelAndView("/template/action/list");
        try {
            Criteria criteria = new SimpleCriteria();
            criteria.addCondition(0,new Condition("module_id","=",moduleId));
            mv.addObject("moduleId",moduleId);
            Page page = service.getList(criteria);
            mv.addObject("page", page);
        } catch (ServiceException e) {
            logger.error("get template action page error",e);
        }
		return mv;
	}
	
    @RequestMapping(value = "toAdd")
    public ModelAndView toCreate(Long moduleId) {
    	ModelAndView mv = new ModelAndView("/template/action/list");
		mv.addObject("moduleId", moduleId);
		return mv;
    }

    @RequestMapping(value = "toEdit")
    public ModelAndView toEdit(Long id) {
        ModelAndView mv = new ModelAndView("/template/action/edit");
        try {
            Action object = service.getObject(id);
            mv.addObject("object", object);
        } catch (ServiceException e) {
            logger.error("get action error",e);
        }
        return mv;
    }

    @RequestMapping(value = "create", method = { RequestMethod.POST })
    public RedirectView create(Action action) {
        RedirectView rv = new RedirectView("page");
        try {
            service.create(action);
        } catch (ServiceException e) {
            logger.error("create action error",e);
        }
        rv.addStaticAttribute("moduleId",action.getModuleId());
        return rv;
    }

    @RequestMapping(value = "edit", method = { RequestMethod.POST })
    public RedirectView edit(Action action) {
        RedirectView rv = new RedirectView("page");
        try {
            service.update(action);
        } catch (ServiceException e) {
            logger.error("update action error", e);
        }
        rv.addStaticAttribute("moduleId",action.getModuleId());
        return rv;
    }

    @RequestMapping(value = "delete")
    public RedirectView delete(Long id,Long moduleId) {
        RedirectView rv = new RedirectView("page");
        try {
            service.delete(id);
        } catch (ServiceException e) {
            logger.error("delete action error",e);
        }
        rv.addStaticAttribute("moduleId",moduleId);
        return rv;
    }
    
}