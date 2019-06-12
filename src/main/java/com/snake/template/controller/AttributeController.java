package com.snake.template.controller;

import com.snake.system.controller.BasicController;
import com.base.util.Condition;
import com.base.util.Criteria;
import com.base.util.SimpleCriteria;
import com.snake.template.model.Attribute;
import com.snake.template.service.IAttributeService;
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
@RequestMapping("/attribute/")
public class AttributeController extends BasicController {
	
	@Resource(name="attributeService")
	private IAttributeService service;

    @RequestMapping(value = "page")
    public ModelAndView page(Long moduleId){
        ModelAndView mv = new ModelAndView("/template/attribute/list");
        try {
            Criteria criteria = new SimpleCriteria();
            criteria.addCondition(0,new Condition("module_id","=",moduleId));
            mv.addObject("moduleId",moduleId);
            Page page = service.getList(criteria);
            mv.addObject("page", page);
        } catch (Exception e) {
            logger.error("get template attribute page error",e);
        }
        return mv;
    }

    @RequestMapping(value = "toAdd")
    public ModelAndView toCreate(Long moduleId) {
        ModelAndView mv = new ModelAndView("/template/attribute/list");
        mv.addObject("moduleId", moduleId);
        return mv;
    }

    @RequestMapping(value = "toEdit")
    public ModelAndView toEdit(Long id) {
        ModelAndView mv = new ModelAndView("/template/attribute/edit");
        try {
            Attribute object = service.getObject(id);
            mv.addObject("object", object);
        } catch (Exception e) {
            logger.error("get attribute error",e);
        }
        return mv;
    }

    @RequestMapping(value = "create", method = { RequestMethod.POST })
    public RedirectView create(Attribute attribute) {
        RedirectView rv = new RedirectView("page");
        try {
            service.create(attribute);
        } catch (Exception e) {
            logger.error("create attribute error",e);
        }
        rv.addStaticAttribute("moduleId",attribute.getModuleId());
        return rv;
    }

    @RequestMapping(value = "edit", method = { RequestMethod.POST })
    public RedirectView edit(Attribute attribute) {
        RedirectView rv = new RedirectView("page");
        try {
            service.update(attribute);
        } catch (Exception e) {
            logger.error("update attribute error", e);
        }
        rv.addStaticAttribute("moduleId",attribute.getModuleId());
        return rv;
    }

    @RequestMapping(value = "delete")
    public RedirectView delete(Long id,Long moduleId) {
        RedirectView rv = new RedirectView("page");
        try {
            service.delete(id);
        } catch (Exception e) {
            logger.error("delete attribute error",e);
        }
        rv.addStaticAttribute("moduleId",moduleId);
        return rv;
    }
    
}