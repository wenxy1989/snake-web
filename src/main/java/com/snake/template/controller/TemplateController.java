package com.snake.template.controller;

import com.base.Constants;
import com.snake.system.controller.BasicController;
import com.base.util.Condition;
import com.base.util.Criteria;
import com.base.util.SimpleCriteria;
import com.snake.template.model.TemplateConfig;
import com.snake.template.service.ITemplateService;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/template/info/")
public class TemplateController extends BasicController {

    @Resource(name = "templateService")
    private ITemplateService templateService;

    @RequestMapping(value = "page")
    public ModelAndView page(Integer pageNo, Integer pageSize, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/template/info/list");
        String name = request.getParameter("name");
        String frameId = request.getParameter("frameId");
        String type = request.getParameter("type");
        try {
            Criteria criteria = new SimpleCriteria();
            if (StringUtils.isNotBlank(name)) {
                criteria.addCondition(0, new Condition("name_", "like", "%" + name + "%"));
                mv.addObject("name", name);
            }
            if (StringUtils.isNotBlank(frameId)) {
                criteria.addCondition(0, new Condition("frame_id", frameId));
                mv.addObject("frameId", frameId);
            }
            if (StringUtils.isNotBlank(type)) {
                criteria.addCondition(0, new Condition("type_", type));
                mv.addObject("type", type);
            }
            criteria.setPageNo(null == pageNo ? Constants.PAGE_NO : pageNo);
            criteria.setFetchSize(null == pageSize ? Constants.PAGE_SIZE : pageSize);
            Page page = templateService.getList(criteria);
            mv.addObject("page", page);
        } catch (Exception e) {
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
    public RedirectView add(TemplateConfig template) {
        RedirectView rv = new RedirectView("page");
        try {
            templateService.create(template);
        } catch (Exception e) {
            logger.error("create template error", e);
        }
        return rv;
    }

    @RequestMapping(value = "toEdit")
    public ModelAndView toEdit(Long id) {
        ModelAndView mv = new ModelAndView("/template/info/edit");
        try {
            TemplateConfig object = templateService.getObject(id);
            mv.addObject("object", object);
        } catch (Exception e) {
            logger.error("get template error", e);
        }
        return mv;
    }

    @RequestMapping(value = "edit", method = {RequestMethod.POST})
    public RedirectView edit(TemplateConfig template) {
        RedirectView rv = new RedirectView("page");
        try {
            templateService.update(template);
        } catch (Exception e) {
            logger.error("update template error");
        }
        return rv;
    }

    @RequestMapping(value = "delete")
    public RedirectView delete(Long id) {
        RedirectView rv = new RedirectView("page");
        try {
            templateService.delete(id);
        } catch (Exception e) {
            logger.error("delete template error");
        }
        return rv;
    }

}