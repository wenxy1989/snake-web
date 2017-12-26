package com.snake.inter.controller;

import com.base.Constants;
import com.snake.system.controller.BasicController;
import com.base.exception.ServiceException;
import com.snake.freemarker.FreeMarkerUtils;
import com.base.util.*;
import com.snake.inter.model.Application;
import com.snake.inter.service.IApplicationService;
import com.snake.system.model.User;
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
import java.util.List;

/**
 * Created by wenxy
 * 2016-11-30 20:25:18.
 */
@Controller
@RequestMapping("/inter/application/")
public class ApplicationController extends BasicController {

    @Resource(name = "applicationService")
    private IApplicationService applicationService;

    @Resource(name = "templateService")
    private ITemplateService templateService;

    @RequestMapping(value = "page", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView page(Integer pageNo, Integer size, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/inter/application/list");
        responseTip(mv, request);
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        Criteria cri = new SimpleCriteria();
        if (StringUtils.isNotBlank(name)) {
            cri.addCondition(0, new Condition("name_", "like", name + "%"));
            mv.addObject("name", name);
        }
        if (StringUtils.isNotBlank(type)) {
            cri.addCondition(0, new Condition("type_", "=", Integer.valueOf(type)));
            mv.addObject("type", type);
        }
        cri.setFetchSize(size == null ? Constants.PAGE_SIZE : size);
        cri.setPageNo(pageNo == null ? 1 : pageNo);
        try {
            Page page = applicationService.getList(cri);
            mv.addObject("page", page);
        } catch (ServiceException e) {
            logger.error("find application page error", e);
        }
        return mv;
    }

    @RequestMapping(value = "details", method = RequestMethod.GET)
    public ModelAndView details(Long id) {
        ModelAndView mv = new ModelAndView("/inter/application/details");
        try {
            Application application = applicationService.getObject(id);
            mv.addObject("application", application);
        } catch (ServiceException e) {
            logger.error("find application error.", e);
        }
        return mv;
    }

    @RequestMapping(value = "toAdd", method = RequestMethod.GET)
    public ModelAndView toAdd(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/inter/application/add");
        return mv;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public RedirectView add(Application application, HttpServletRequest request) {
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try {
            Application object = applicationService.getObjectByCode(application.getCode());
            if (object == null) {
                User loginUser = getLoginUser(request);
                Long creatorId = loginUser.getId();
                application.setCreatedTime(DateTimeUtils.getNowDateTime());
                application.setCreatorId(creatorId);
                applicationService.create(application);
                result = RESULT_ADD_SUCCESS;
            }
        } catch (Exception e) {
            logger.error("create application error", e);
        }
        rv.addStaticAttribute(OPE_RESULT, result);
        return rv;
    }

    @RequestMapping(value = "toEdit", method = RequestMethod.GET)
    public ModelAndView toEdit(Long id) {
        ModelAndView mv = new ModelAndView("/inter/application/edit");
        try {
            Application application = applicationService.getObject(id);
            mv.addObject("application", application);
        } catch (ServiceException e) {
            logger.error("find application error.", e);
        }
        return mv;
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public RedirectView update(Application application) {
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try {
            if (StringUtils.isNotBlank(application.getCode())) {
                applicationService.update(application);
                result = RESULT_EDIT_SUCCESS;
            }
        } catch (Exception e) {
            logger.error("update application error", e);
        }
        rv.addStaticAttribute(OPE_RESULT, result);
        return rv;
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public RedirectView delete(Long id) {
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try {
            applicationService.delete(id);
            result = RESULT_DELETE_SUCCESS;
        } catch (Exception e) {
            logger.error("delete application error", e);
        }
        rv.addStaticAttribute(OPE_RESULT, result);
        return rv;
    }

    @ResponseBody
    @RequestMapping(value = "write")
    public Object write(Long id) {
        String result =  RESULT_ERROR;
        try {
            Application application = applicationService.getDetails(id);
            if(null != application) {
                List<Template> templates = templateService.getListByType(application.getType());
                FreeMarkerUtils.getInstance().buildApplication(application, templates);
            }
            result = RESULT_SUCCESS;
        } catch (ServiceException e) {
            logger.error("write application template code error", e);
        }
        return result;
    }
}
