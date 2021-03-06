package com.snake.template.controller;

import com.snake.system.controller.BasicController;
import com.base.exception.ServiceException;
import com.base.util.Criteria;
import com.snake.freemarker.FreeMarkerUtils;
import com.base.util.SimpleCriteria;
import com.snake.template.model.Action;
import com.snake.template.model.Attribute;
import com.snake.template.model.Module;
import com.snake.template.model.Template;
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
        } catch (ServiceException e) {
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
        }catch (ServiceException e){
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
        } catch (ServiceException e) {
            logger.error("get module error",e);
        }
        return mv;
    }

    @RequestMapping(value = "edit", method = {RequestMethod.POST})
    public RedirectView edit(Module module) {
        RedirectView rv = new RedirectView("page");
        try {
            service.update(module);
        }catch (ServiceException e){
            logger.error("update module error");
        }
        return rv;
    }

    @RequestMapping(value = "delete")
    public RedirectView delete(Long id) {
        RedirectView rv = new RedirectView("page");
        try {
            service.delete(id);
        }catch (ServiceException e){
            logger.error("delete module error");
        }
        return rv;
    }

    @ResponseBody
    @RequestMapping(value = "export_code")
    public void exportCode(Long id) {
        try {
            Module module = service.getObject(id);
            List<Attribute> attributes = attributeService.getListByModuleId(module.getId());
            List<Action> actions = actionService.getListByModuleId(module.getId());
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("attributes", attributes);
            params.put("actions", actions);
            params.put(FreeMarkerUtils.CLASS_NAME, new String(module.getClassName()));
            params.put(FreeMarkerUtils.MODULE_CODE, module.getClassName().toLowerCase());
            params.put(FreeMarkerUtils.MODULE_NAME, module.getName());
            params.put("author", "wenxy");
            params.put("date", new Date());
            List<Template> templates = templateService.getListByType("base_template");
            for (Template each : templates) {
                FreeMarkerUtils.getInstance().buildTemplate(each.getGroup(), each.getName(), params, each.getSavePathModel(), each.getSaveFileModel());
            }
        } catch (ServiceException e) {
            logger.error("export module error",e);
        }
    }

}