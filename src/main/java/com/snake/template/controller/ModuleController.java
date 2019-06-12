package com.snake.template.controller;

import com.snake.system.controller.BasicController;
import com.base.util.Criteria;
import com.snake.freemarker.FreeMarkerUtils;
import com.base.util.SimpleCriteria;
import com.snake.template.model.Action;
import com.snake.template.model.Attribute;
import com.snake.template.model.Module;
import com.snake.template.model.TemplateConfig;
import com.snake.template.service.IActionService;
import com.snake.template.service.IAttributeService;
import com.snake.template.service.IModuleService;
import com.snake.template.service.ITemplateService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/module/")
public class ModuleController extends BasicController {

    @Resource(name = "moduleService")
    private IModuleService service;

    @Resource(name = "actionService")
    private IActionService actionService;

    @Resource(name = "templateService")
    private ITemplateService templateService;

    @Resource(name = "attributeService")
    private IAttributeService attributeService;

    @RequestMapping(value = "page")
    public ModelAndView page() {
        ModelAndView mv = new ModelAndView("/template/module/list");
        try {
            Criteria criteria = new SimpleCriteria();
            Page page = service.getList(criteria);
            mv.addObject("page", page);
        } catch (Exception e) {
            logger.error("query module page error", e);
        }
        return mv;
    }

    @RequestMapping(value = "toAdd")
    public ModelAndView toCreate() {
        ModelAndView mv = new ModelAndView("/template/module/add");
        return mv;
    }

    @RequestMapping(value = "create", method = {RequestMethod.POST})
    public RedirectView create(Module module) {
        RedirectView rv = new RedirectView("page");
        try {
            service.create(module);
        }catch (Exception e){
            logger.error("create module error");
        }
        return rv;
    }

    @RequestMapping(value = "toEdit")
    public ModelAndView toEdit(Long id) {
        ModelAndView mv = new ModelAndView("/template/module/edit");
        try {
            Module object = service.getObject(id);
            mv.addObject("object", object);
        } catch (Exception e) {
            logger.error("get module error",e);
        }
        return mv;
    }

    @RequestMapping(value = "edit", method = {RequestMethod.POST})
    public RedirectView edit(Module module) {
        RedirectView rv = new RedirectView("page");
        try {
            service.update(module);
        }catch (Exception e){
            logger.error("update module error");
        }
        return rv;
    }

    @RequestMapping(value = "delete")
    public RedirectView delete(Long id) {
        RedirectView rv = new RedirectView("page");
        try {
            service.delete(id);
        }catch (Exception e){
            logger.error("delete module error");
        }
        return rv;
    }

}