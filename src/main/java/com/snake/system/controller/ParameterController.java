package com.snake.system.controller;

import com.base.Constants;
import com.base.exception.ServiceException;
import com.base.util.Condition;
import com.base.util.Criteria;
import com.base.util.DateTimeUtils;
import com.base.util.SimpleCriteria;
import com.snake.system.model.Parameter;
import com.snake.system.service.IParameterService;
import com.snake.system.model.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * User: wenxy
 * Date: 2016-11-2 18:25:54
 */
@Controller
@Repository("sysParameterController")
@RequestMapping("/sys/parameter/")
public class ParameterController extends BasicController {

    @Resource(name = "sysParameterService")
    private IParameterService parameterService;

    @RequestMapping(value = "page", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView page(Integer pageNo, Integer size, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/system/parameter/list");
        String ope_result = request.getParameter(OPE_RESULT);
        if (StringUtils.isNotBlank(ope_result)) {
            mv.addObject(OPE_RESULT, ope_result + "," + System.currentTimeMillis());
        }
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        Criteria cri = new SimpleCriteria();
        if (StringUtils.isNotBlank(name)) {
            cri.addCondition(0, new Condition("name_", "like", name + "%"));
            mv.addObject("name", name);
        }
        if (StringUtils.isNotBlank(code)) {
            cri.addCondition(0, new Condition("code_", "like", code + "%"));
            mv.addObject("code", code);
        }
        cri.setPageNo(pageNo == null ? 1 : pageNo);
        cri.setFetchSize(size == null ? Constants.PAGE_SIZE : size);
        try {
            Page page = parameterService.getList(cri);
            mv.addObject("page", page);
        } catch (ServiceException e) {
            logger.error("获取属性信息失败", e);
        }
        return mv;
    }

    @RequestMapping(value = "toAdd")
    public ModelAndView toAdd() {
        ModelAndView mv = new ModelAndView("/system/parameter/add");
        return mv;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public RedirectView add(Parameter parameter, HttpServletRequest request) {
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try {
            Parameter object = parameterService.getObjectByCode(parameter.getCode());
            if (object != null) {
                result = RESULT_EXISTS;
            } else {
                User loginUser = getLoginUser(request);
                Long creatorId = loginUser.getId();
                parameter.setCreatedTime(DateTimeUtils.getNowDateTime());
                parameter.setCreatorId(creatorId);
                parameterService.create(parameter);
                result = RESULT_ADD_SUCCESS;
            }
        } catch (Exception e) {
            logger.error("添加属性信息失败", e);
        }
        rv.addStaticAttribute(OPE_RESULT, result);
        return rv;
    }

    @RequestMapping(value = "toEdit", method = RequestMethod.GET)
    public ModelAndView edit(Long id) {
        ModelAndView mv = new ModelAndView("/system/parameter/edit");
        try {
            Parameter parameter = parameterService.getObject(id);
            mv.addObject("obj", parameter);
        } catch (ServiceException e) {
            logger.error("获取属性信息失败.", e);
        }
        return mv;
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public RedirectView update(Parameter parameter) {
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try {
            parameterService.update(parameter);
            result = RESULT_EDIT_SUCCESS;
        } catch (Exception e) {
            logger.error("修改属性信息失败", e);
        }
        rv.addStaticAttribute(OPE_RESULT, result);
        return rv;
    }

    @RequestMapping(value = "delete")
    public RedirectView delete(Long id) {
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try {
            parameterService.delete(id);
            result = RESULT_DELETE_SUCCESS;
        } catch (Exception e) {
            logger.error("删除属性信息失败", e);
        }
        rv.addStaticAttribute(OPE_RESULT, result);
        return rv;
    }

}