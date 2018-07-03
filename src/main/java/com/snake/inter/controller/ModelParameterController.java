package com.snake.inter.controller;

import com.snake.system.controller.BasicController;
import com.base.exception.ServiceException;
import com.base.util.Condition;
import com.base.util.Criteria;
import com.base.util.DateTimeUtils;
import com.base.util.SimpleCriteria;
import com.snake.inter.model.Model;
import com.snake.inter.model.ModelParameter;
import com.snake.inter.model.Parameter;
import com.snake.inter.service.IModelParameterService;
import com.snake.inter.service.IModelService;
import com.snake.inter.service.IParameterService;
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

@Controller
@RequestMapping("/inter/model/parameter/")
public class ModelParameterController extends BasicController {

    @Resource(name = "modelService")
    private IModelService modelService;

    @Resource(name = "parameterService")
    private IParameterService parameterService;

    @Resource(name = "modelParameterService")
    private IModelParameterService modelParameterService;

    @RequestMapping(value = "page", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView page(Long modelId, Integer pageNo, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/inter/model/parameter/list");
        responseTip(mv, request);
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        String type = request.getParameter("type");
        Criteria cri = new SimpleCriteria();
        cri.addCondition(0, new Condition("model_id", "=", modelId));
        mv.addObject("modelId", modelId);
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
        cri.setFetchSize(-1);
        try {
            Model model = modelService.getObject(modelId);
            mv.addObject("model", model);
            Page page = modelParameterService.getList(cri);
            mv.addObject("page", page);
        } catch (ServiceException e) {
            logger.error("find interface result page error", e);
        }
        return mv;
    }

    @RequestMapping(value = "toAdd")
    public ModelAndView toAdd(Long modelId) {
        ModelAndView mv = new ModelAndView("/inter/model/parameter/add");
        mv.addObject("modelId", modelId);
        return mv;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public RedirectView add(ModelParameter parameter, Long modelId,HttpServletRequest request) {
        RedirectView rv = new RedirectView("page");
        String resultCode = RESULT_ERROR;
        try {
            if (!StringUtils.isBlank(parameter.getCode()) && !StringUtils.isBlank(parameter.getName())) {
                ModelParameter object = modelParameterService.getObject(parameter.getModelId(), parameter.getCode());
                if (object == null) {
                    modelParameterService.create(parameter);
                    Parameter obj = parameter.clone();
                    if (null == parameterService.findOne(obj)) {
                        obj.setCreatorId(getLoginUserId(request));
                        obj.setCreatedTime(DateTimeUtils.getNowDateTime());
                        parameterService.create(obj);
                    }
                    resultCode = RESULT_ADD_SUCCESS;
                } else {
                    resultCode = RESULT_EXISTS;
                }
            }
        } catch (Exception e) {
            logger.error("create interface result error", e);
        }
        rv.addStaticAttribute("modelId", modelId);
        rv.addStaticAttribute(OPE_RESULT, resultCode);
        return rv;
    }

    @RequestMapping(value = "toEdit", method = RequestMethod.GET)
    public ModelAndView toEdit(Long id) {
        ModelAndView mv = new ModelAndView("/inter/model/parameter/edit");
        try {
            ModelParameter parameter = modelParameterService.getObject(id);
            mv.addObject("parameter", parameter);
        } catch (ServiceException e) {
            logger.error("find interface result error.", e);
        }
        return mv;
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public RedirectView update(ModelParameter parameter, Long modelId) {
        RedirectView rv = new RedirectView("page");
        String resultCode = RESULT_ERROR;
        try {
            if (StringUtils.isNotBlank(parameter.getCode())) {
                modelParameterService.update(parameter);
                resultCode = RESULT_EDIT_SUCCESS;
            }
        } catch (Exception e) {
            logger.error("update interface result error", e);
        }
        rv.addStaticAttribute("modelId", modelId);
        rv.addStaticAttribute(OPE_RESULT, resultCode);
        return rv;
    }

    @RequestMapping(value = "delete")
    public RedirectView delete(Long id, Long modelId) {
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try {
            modelParameterService.delete(id);
            result = RESULT_DELETE_SUCCESS;
        } catch (Exception e) {
            logger.error("delete interface Model Parameter error", e);
        }
        rv.addStaticAttribute("modelId", modelId);
        rv.addStaticAttribute(OPE_RESULT, result);
        return rv;
    }

    @ResponseBody
    @RequestMapping(value = "addParameter")
    public Object addParameter(Long modelId, Long parameterId) {
        String resultCode = RESULT_ERROR;
        try {
            Parameter parameter = parameterService.getObject(parameterId);
            if (null != parameter) {
                ModelParameter modelParameter = parameter.cloneParameter(new ModelParameter());
                modelParameter.setKeyType(0);
                modelParameter.setModelId(modelId);
                modelParameter.setAllowBlank(false);
                modelParameterService.create(modelParameter);
                resultCode = RESULT_SUCCESS;
            }
        } catch (ServiceException e) {
            logger.error("add Parameter to model parameter error", e);
        }
        return resultCode;
    }

}