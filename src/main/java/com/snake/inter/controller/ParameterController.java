package com.snake.inter.controller;

import com.base.Constants;
import com.snake.system.controller.BasicController;
import com.base.util.Condition;
import com.base.util.Criteria;
import com.base.util.DateTimeUtils;
import com.base.util.SimpleCriteria;
import com.snake.inter.model.Parameter;
import com.snake.inter.service.IParameterService;
import com.snake.system.model.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/inter/parameter/")
public class ParameterController extends BasicController {

    @Resource(name = "parameterService")
    private IParameterService parameterService;

    @RequestMapping(value = "page", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView page(Integer pageNo, Integer size, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/inter/parameter/list");
        responseTip(mv, request);
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        String type = request.getParameter("type");
        Criteria cri = new SimpleCriteria();
        if (StringUtils.isNotBlank(name)) {
            cri.addCondition(0, new Condition("name_", "like", name + "%"));
            mv.addObject("name", name);
        }
        if (StringUtils.isNotBlank(code)) {
            cri.addCondition(0, new Condition("code_", "like", code + "%"));
            mv.addObject("code", code);
        }
        if (StringUtils.isNotBlank(type)) {
            cri.addCondition(0, new Condition("type_", "=", type));
            mv.addObject("type", type);
        }
        cri.setPageNo(pageNo == null ? 1 : pageNo);
        cri.setFetchSize(size == null ? Constants.PAGE_SIZE : size);
        try {
            Page page = parameterService.getList(cri);
            mv.addObject("page", page);
        } catch (Exception e) {
            logger.error("find interface parameter page error", e);
        }
        return mv;
    }

    @RequestMapping(value = "toAdd")
    public ModelAndView toAdd() {
        ModelAndView mv = new ModelAndView("/inter/parameter/add");
        return mv;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public RedirectView add(Parameter parameter, HttpServletRequest request) {
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try {
            if (!StringUtils.isBlank(parameter.getCode()) && !StringUtils.isBlank(parameter.getName())) {
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
            }
        } catch (Exception e) {
            logger.error("create interface parameter error", e);
        }
        rv.addStaticAttribute(OPE_RESULT, result);
        return rv;
    }

    @RequestMapping(value = "toEdit", method = RequestMethod.GET)
    public ModelAndView toEdit(Long id) {
        ModelAndView mv = new ModelAndView("/inter/parameter/edit");
        try {
            Parameter parameter = parameterService.getObject(id);
            mv.addObject("parameter", parameter);
        } catch (Exception e) {
            logger.error("find interface parameter error.", e);
        }
        return mv;
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public RedirectView update(Parameter parameter) {
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try {
            if (StringUtils.isNotBlank(parameter.getCode())) {
                parameterService.update(parameter);
                result = RESULT_EDIT_SUCCESS;
            }
        } catch (Exception e) {
            logger.error("update interface parameter error", e);
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
            logger.error("delete interface parameter error", e);
        }
        rv.addStaticAttribute(OPE_RESULT, result);
        return rv;
    }

    @RequestMapping(value = "select")
    public ModelAndView select(Integer pageNo, Integer size, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("/inter/parameter/select");
        responseTip(mv, request);
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        String type = request.getParameter("type");
        String params = request.getParameter("params");
        mv.addObject("params", params);
        Criteria cri = new SimpleCriteria();
        cri.setPageNo(pageNo == null ? 1 : pageNo);
        cri.setFetchSize(size == null ? Constants.PAGE_SIZE : size);
        if (StringUtils.isNotBlank(params) && !params.contains("/")) {
            cri.setLike(params);
            try {
                Page page = parameterService.getList(cri);
                mv.addObject("page", page);
            } catch (Exception e) {
                logger.error("find interface parameter by like page error", e);
            }
        } else {
            if (StringUtils.isNotBlank(params) && params.contains("/")) {
                String[] paramArray = params.split("/");
                name = paramArray[0];
                code = paramArray[1];
                if (paramArray.length > 2) {
                    type = paramArray[2];
                }
            }
            if (StringUtils.isNotBlank(name)) {
                cri.addCondition(0, new Condition("name_", "like", name + "%"));
                mv.addObject("name", name);
            }
            if (StringUtils.isNotBlank(code)) {
                cri.addCondition(0, new Condition("code_", "like", code + "%"));
                mv.addObject("code", code);
            }
            if (StringUtils.isNotBlank(type)) {
                cri.addCondition(0, new Condition("type_", "=", type));
                mv.addObject("type", type);
            }
            try {
                Page page = parameterService.getList(cri);
                mv.addObject("page", page);
            } catch (Exception e) {
                logger.error("find interface parameter page error", e);
            }
        }
        return mv;
    }

}