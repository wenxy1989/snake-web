package com.snake.template.controller;

import com.base.Constants;
import com.snake.system.controller.BasicController;
import com.base.exception.ServiceException;
import com.base.util.Condition;
import com.base.util.Criteria;
import com.snake.freemarker.FreeMarkerUtils;
import com.base.util.SimpleCriteria;
import com.snake.template.model.Template;
import com.snake.template.service.ITemplateService;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/template/info/")
public class TemplateController extends BasicController {

    @Resource(name = "templateService")
    private ITemplateService templateService;

    @RequestMapping(value = "page")
    public ModelAndView page(Integer pageNo, Integer pageSize, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/template/info/list");
        String name = request.getParameter("name");
        String group = request.getParameter("group");
        String type = request.getParameter("type");
        try {
            Criteria criteria = new SimpleCriteria();
            if (StringUtils.isNotBlank(name)) {
                criteria.addCondition(0, new Condition("name_", "like", "%" + name + "%"));
                mv.addObject("name", name);
            }
            if (StringUtils.isNotBlank(group)) {
                criteria.addCondition(0, new Condition("group_", "like", "%" + group + "%"));
                mv.addObject("group", group);
            }
            if (StringUtils.isNotBlank(type)) {
                criteria.addCondition(0, new Condition("type_", "like", "%" + type + "%"));
                mv.addObject("type", type);
            }
            criteria.setPageNo(null == pageNo ? Constants.PAGE_NO : pageNo);
            criteria.setFetchSize(null == pageSize ? Constants.PAGE_SIZE : pageSize);
            Page page = templateService.getList(criteria);
            mv.addObject("page", page);
        } catch (ServiceException e) {
            logger.error("query template page error", e);
        }
        return mv;
    }

    @RequestMapping(value = "toAdd")
    public ModelAndView toCreate() {
        ModelAndView mv = new ModelAndView("/template/info/add");
        return mv;
    }

    @RequestMapping(value = "add", method = {RequestMethod.POST})
    public RedirectView add(Template template) {
        RedirectView rv = new RedirectView("page");
        try {
            templateService.create(template);
        } catch (ServiceException e) {
            logger.error("create template error");
        }
        return rv;
    }

    @RequestMapping(value = "toEdit")
    public ModelAndView toEdit(Long id) {
        ModelAndView mv = new ModelAndView("/template/info/edit");
        try {
            Template object = templateService.getObject(id);
            mv.addObject("object", object);
        } catch (ServiceException e) {
            logger.error("get template error", e);
        }
        return mv;
    }

    @RequestMapping(value = "edit", method = {RequestMethod.POST})
    public RedirectView edit(Template template) {
        RedirectView rv = new RedirectView("page");
        try {
            templateService.update(template);
        } catch (ServiceException e) {
            logger.error("update template error");
        }
        return rv;
    }

    @RequestMapping(value = "delete")
    public RedirectView delete(Long id) {
        RedirectView rv = new RedirectView("page");
        try {
            templateService.delete(id);
        } catch (ServiceException e) {
            logger.error("delete template error");
        }
        return rv;
    }

    @ResponseBody
    @RequestMapping(value = "export_code")
    public void exportCode(Long id) {
        try {
            Template template = templateService.getObject(id);
            Map<String, Object> params = new HashMap<String, Object>();
            params.put(FreeMarkerUtils.MODULE_NAME, template.getGroup());
            FreeMarkerUtils.getInstance().buildTemplate(template.getGroup(), template.getName(), params, template.getSavePathModel(), template.getSaveFileModel());
        } catch (ServiceException e) {
            logger.error("export template error", e);
        }
    }

//    @ExceptionHandler
//    public @ResponseBody Object exception(Exception e) {
//        return "exception : "+e.getMessage();
//    }

}