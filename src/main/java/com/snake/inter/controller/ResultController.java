package com.snake.inter.controller;

import com.snake.system.controller.BasicController;
import com.base.util.DateTimeUtils;
import com.snake.inter.model.*;
import com.snake.inter.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/inter/result/")
public class ResultController extends BasicController {

    @Resource(name = "resultService")
    private IResultService resultService;

    @Resource(name = "urlService")
    private IUrlService urlService;

    @Resource(name = "parameterService")
    private IParameterService parameterService;

    @Resource(name = "groupService")
    private IGroupService groupService;

    @Resource(name = "modelParameterService")
    private IModelParameterService modelParameterService;

    @RequestMapping(value = "{urlId}/list", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView list(@PathVariable Long urlId, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("/inter/result/list");
        responseTip(mv, request);
        try {
            Url url = urlService.getObject(urlId);
            mv.addObject("url", url);
            List<Result> list = resultService.getListByUrlId(urlId);
            mv.addObject("list", list);
            if (null != list && list.size() > 0) {
                for (Result obj : list) {
                    String type = obj.getType();
                    if ("Object".equals(type) || "Array".equals(type)) {
                        String model = obj.getExample();
                        Group group = groupService.getObjectByModel(model);
                        if (null != group) {
                            List<ModelParameter> parameters = modelParameterService.getListByModelId(group.getId());
                            mv.addObject("parameters", parameters);
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("find interface result list error", e);
        }
        mv.addObject("urlId", urlId);
        return mv;
    }

    @RequestMapping(value = "{urlId}/toAdd")
    public ModelAndView toAdd(@PathVariable Long urlId) {
        ModelAndView mv = new ModelAndView("/inter/result/add");
        mv.addObject("urlId", urlId);
        return mv;
    }

    @RequestMapping(value = "{urlId}/add", method = RequestMethod.POST)
    public RedirectView add(@PathVariable Long urlId,Result result,HttpServletRequest request) {
        RedirectView rv = new RedirectView("list");
        String resultCode = RESULT_ERROR;
        try {
            if (!StringUtils.isBlank(result.getCode()) && !StringUtils.isBlank(result.getName())) {
                resultService.create(result);
                Parameter obj = result.clone(Parameter.class);
                Parameter parameter = parameterService.findOne(obj);
                if (null == parameter) {
                    obj.setCreatorId(getLoginUserId(request));
                    obj.setCreatedTime(DateTimeUtils.getNowDateTime());
                    parameterService.create(obj);
                }
                resultCode = RESULT_ADD_SUCCESS;
            }
        } catch (Exception e) {
            logger.error("create interface result error", e);
        }
        rv.addStaticAttribute(OPE_RESULT, resultCode);
        rv.addStaticAttribute("urlId", urlId);
        return rv;
    }

    @RequestMapping(value = "{urlId}/toEdit", method = RequestMethod.GET)
    public ModelAndView toEdit(@PathVariable Long urlId,Long id) {
        ModelAndView mv = new ModelAndView("/inter/result/edit");
        try {
            Result result = resultService.getObject(id);
            mv.addObject("result", result);
        } catch (Exception e) {
            logger.error("find interface result error.", e);
        }
        mv.addObject("urlId",urlId);
        return mv;
    }

    @RequestMapping(value = "{urlId}/edit", method = RequestMethod.POST)
    public RedirectView update(@PathVariable Long urlId,Result result) {
        RedirectView rv = new RedirectView("list");
        String resultCode = RESULT_ERROR;
        try {
            if (StringUtils.isNotBlank(result.getCode())) {
                resultService.update(result);
                resultCode = RESULT_EDIT_SUCCESS;
            }
        } catch (Exception e) {
            logger.error("update interface result error", e);
        }
        rv.addStaticAttribute(OPE_RESULT, resultCode);
        rv.addStaticAttribute("urlId", urlId);
        return rv;
    }

    @RequestMapping(value = "{urlId}/delete")
    public RedirectView delete(@PathVariable Long urlId,Long id) {
        RedirectView rv = new RedirectView("list");
        String result = RESULT_ERROR;
        try {
            resultService.delete(id);
            result = RESULT_DELETE_SUCCESS;
        } catch (Exception e) {
            logger.error("delete interface result error", e);
        }
        rv.addStaticAttribute(OPE_RESULT, result);
        rv.addStaticAttribute("urlId", urlId);
        return rv;
    }

    @ResponseBody
    @RequestMapping(value = "{urlId}/addParameter")
    public Object addParameter(@PathVariable Long urlId, Long parameterId) {
        String resultCode = RESULT_ERROR;
        try {
            Parameter parameter = parameterService.getObject(parameterId);
            if (null != parameter) {
                Result result = parameter.clone(Result.class);
                result.setUrlId(urlId);
                result.setAllowBlank(false);
                resultService.create(result);
                resultCode = RESULT_SUCCESS;
            }
        } catch (Exception e) {
            logger.error("add parameter to url result error", e);
        }
        return resultCode;
    }

    @ResponseBody
    @RequestMapping(value = "{urlId}/importModelParameters")
    public Object importModelParameters(@PathVariable Long urlId, Long modelId) {
        String resultCode = RESULT_ERROR;
        try {
            List<ModelParameter> parameters = modelParameterService.getListByModelId(modelId);
            if (null != parameters && parameters.size() > 0) {
                for (ModelParameter parameter : parameters) {
                    Result obj = parameter.clone(Result.class);
                    obj.setUrlId(urlId);
                    obj.setAllowBlank(false);
                    resultService.create(obj);
                }
                resultCode = RESULT_SUCCESS;
            }
        } catch (Exception e) {
            logger.error("add parameter to url result error", e);
        }
        return resultCode;
    }

}