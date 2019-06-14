package com.snake.inter.controller;

import com.base.Constants;
import com.snake.system.controller.BasicController;
import com.snake.freemarker.FreeMarkerUtils;
import com.base.util.*;
import com.snake.inter.model.Application;
import com.snake.inter.model.Model;
import com.snake.inter.model.ModelParameter;
import com.snake.inter.service.IApplicationService;
import com.snake.inter.service.IModelParameterService;
import com.snake.inter.service.IModelService;
import com.snake.system.model.User;
import com.snake.system.service.IParameterService;
import com.snake.template.model.Frame;
import com.snake.template.model.TemplateConfig;
import com.snake.template.service.IFrameService;
import com.snake.template.service.ITemplateService;
import net.minidev.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
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
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/inter/model/")
public class ModelController extends BasicController {

    @Resource(name = "applicationService")
    private IApplicationService applicationService;
    @Resource(name = "modelService")
    private IModelService modelService;
    @Resource(name = "modelParameterService")
    private IModelParameterService modelParameterService;
    @Resource(name = "templateService")
    private ITemplateService templateService;
    @Resource(name = "frameService")
    private IFrameService frameService;

    @RequestMapping(value = "{applicationId}/page", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView applicationPage(@PathVariable Long applicationId, Integer pageNo, Integer size, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/inter/model/list");
        responseTip(mv, request);
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        String status = request.getParameter("status");
        String mine = request.getParameter("mine");
        status = org.apache.commons.lang.StringUtils.isNotBlank(status) ? status : "0";
        mine = org.apache.commons.lang.StringUtils.isNotBlank(mine) ? mine : "0";
        Criteria cri = new SimpleCriteria();
        cri.addCondition(0, new Condition("application_id", "=", applicationId));
        if (org.apache.commons.lang.StringUtils.isNotBlank(name)) {
            cri.addCondition(0, new Condition("name_", "like", name + "%"));
            mv.addObject("name", name);
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(code)) {
            cri.addCondition(0, new Condition("code_", "like", code + "%"));
            mv.addObject("code", code);
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(status)) {
            cri.addCondition(0, new Condition("status_", "=", Integer.valueOf(status)));
            mv.addObject("status", status);
        }
        if ("1".equals(mine)) {
            Long userId = getLoginUserId(request);
            cri.addCondition(0, new Condition("creator_id", "=", userId));
            mv.addObject("mine", mine);
        }
        mv.addObject("mine", mine);
        cri.setFetchSize(size == null ? Constants.PAGE_SIZE : size);
        cri.setPageNo(pageNo == null ? 1 : pageNo);
        try {
            Page page = modelService.getList(cri);
            mv.addObject("page", page);
            List<Frame> frameList = frameService.getAll();
            mv.addObject("frameList",frameList);
        } catch (Exception e) {
            logger.error("find interface model page error", e);
        }
        mv.addObject("applicationId", applicationId);
        return mv;
    }

    @RequestMapping(value = "{applicationId}/toAdd", method = RequestMethod.GET)
    public ModelAndView toAdd(@PathVariable Long applicationId, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/inter/model/add");
        mv.addObject("applicationId", applicationId);
        return mv;
    }

    @RequestMapping(value = "{applicationId}/add", method = RequestMethod.POST)
    public RedirectView add(@PathVariable Long applicationId, Model model, HttpServletRequest request) {
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try {
            User loginUser = getLoginUser(request);
            Long creatorId = loginUser.getId();
            model.setCreatedTime(DateTimeUtils.getNowDateTime());
            model.setCreatorId(creatorId);
            model.setStatus(0);//草稿
            modelService.create(model);
            result = RESULT_ADD_SUCCESS;
        } catch (Exception e) {
            logger.error("create interface model error", e);
        }
        rv.addStaticAttribute("applicationId", applicationId);
        rv.addStaticAttribute(OPE_RESULT, result);
        return rv;
    }

    @RequestMapping(value = "{applicationId}/toEdit", method = RequestMethod.GET)
    public ModelAndView toEdit(@PathVariable Long applicationId, Long id) {
        ModelAndView mv = new ModelAndView("/inter/model/edit");
        mv.addObject("applicationId", applicationId);
        try {
            Model model = modelService.getObject(id);
            mv.addObject("model", model);
        } catch (Exception e) {
            logger.error("find interface model error.", e);
        }
        return mv;
    }

    @RequestMapping(value = "{applicationId}/edit", method = RequestMethod.POST)
    public RedirectView update(@PathVariable Long applicationId, Model model) {
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try {
            modelService.update(model);
            result = RESULT_EDIT_SUCCESS;
        } catch (Exception e) {
            logger.error("update interface model error", e);
        }
        rv.addStaticAttribute("applicationId", applicationId);
        rv.addStaticAttribute(OPE_RESULT, result);
        return rv;
    }

    @RequestMapping(value = "{applicationId}/delete", method = RequestMethod.GET)
    public RedirectView delete(@PathVariable Long applicationId, Long id) {
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try {
            modelService.delete(id);
            result = RESULT_DELETE_SUCCESS;
        } catch (Exception e) {
            logger.error("delete interface model error", e);
        }
        rv.addStaticAttribute("applicationId", applicationId);
        rv.addStaticAttribute(OPE_RESULT, result);
        return rv;
    }

    @RequestMapping(value = "{applicationId}/submit", method = RequestMethod.GET)
    public RedirectView submit(@PathVariable Long applicationId, Long id) {
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try {
            Model model = modelService.getObject(id);
            if (null != model && model.getStatus() == 0 || 2 == model.getStatus()) {//status is outline or return
                model.setStatus(1);
                modelService.update(model);
                result = RESULT_EDIT_SUCCESS;
            }
        } catch (Exception e) {
            logger.error("submit interface model error", e);
        }
        rv.addStaticAttribute("applicationId", applicationId);
        rv.addStaticAttribute(OPE_RESULT, result);
        return rv;
    }

    @RequestMapping(value = "{applicationId}/publish", method = RequestMethod.GET)
    public RedirectView publish(@PathVariable Long applicationId, Long id) {
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try {
            Model model = modelService.getObject(id);
            if (null != model && model.getStatus() == 1) {//status is submit
                model.setStatus(3);
                modelService.update(model);
                result = RESULT_EDIT_SUCCESS;
            }
        } catch (Exception e) {
            logger.error("publish interface model error", e);
        }
        rv.addStaticAttribute("applicationId", applicationId);
        rv.addStaticAttribute(OPE_RESULT, result);
        return rv;
    }

    @RequestMapping(value = "{applicationId}/return", method = RequestMethod.GET)
    public RedirectView doReturn(@PathVariable Long applicationId, Long id) {
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try {
            Model model = modelService.getObject(id);
            if (null != model && model.getStatus() == 1) {//status is submit
                model.setStatus(2);
                modelService.update(model);
                result = RESULT_EDIT_SUCCESS;
            }
        } catch (Exception e) {
            logger.error("delete interface model error", e);
        }
        rv.addStaticAttribute("applicationId", applicationId);
        rv.addStaticAttribute(OPE_RESULT, result);
        return rv;
    }

    @RequestMapping(value = "{applicationId}/select")
    public ModelAndView applicationSelect(@PathVariable Long applicationId, Integer pageNo, Integer size, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("/inter/model/select");
        responseTip(mv, request);
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        String params = request.getParameter("params");
        mv.addObject("params", params);
        Criteria cri = new SimpleCriteria();
        cri.addCondition(0, new Condition("application_id", "=", applicationId));
        mv.addObject("applicationId", applicationId);
        cri.setPageNo(pageNo == null ? 1 : pageNo);
        cri.setFetchSize(size == null ? Constants.PAGE_SIZE : size);
        if (org.apache.commons.lang.StringUtils.isNotBlank(params) && !params.contains("/")) {
            cri.setLike(params);
            try {
                Page page = modelService.getList(cri);
                mv.addObject("page", page);
            } catch (Exception e) {
                logger.error("find interface model by like page error", e);
            }
        } else {
            if (org.apache.commons.lang.StringUtils.isNotBlank(params) && params.contains("/")) {
                String[] paramArray = params.split("/");
                name = paramArray[0];
                code = paramArray[1];
            }
            if (org.apache.commons.lang.StringUtils.isNotBlank(name)) {
                cri.addCondition(0, new Condition("name_", "like", name + "%"));
                mv.addObject("name", name);
            }
            if (org.apache.commons.lang.StringUtils.isNotBlank(code)) {
                cri.addCondition(0, new Condition("code_", "like", code + "%"));
                mv.addObject("code", code);
            }
            try {
                Page page = modelService.getList(cri);
                mv.addObject("page", page);
            } catch (Exception e) {
                logger.error("find interface model page error", e);
            }
        }
        return mv;
    }

    @RequestMapping(value = "{applicationId}/details", method = RequestMethod.GET)
    public ModelAndView details(@PathVariable Long applicationId, Long id) {
        ModelAndView mv = new ModelAndView("/inter/model/details");
        try {
            Model model = modelService.getObject(id);
            mv.addObject("model", model);
            List<ModelParameter> parameters = modelParameterService.getListByModelId(id);
            mv.addObject("parameters", parameters);
            mv.addObject("now", new Date());
        } catch (Exception e) {
            logger.error("find interface model error.", e);
        }
        return mv;
    }

    @RequestMapping(value = "{applicationId}/inter", method = RequestMethod.GET)
    public ModelAndView modelInter(@PathVariable Long applicationId, Long id) {
        ModelAndView mv = new ModelAndView("/inter/model/inter");
        try {
            Model model = modelService.getObject(id);
            mv.addObject("model", model);
            List<ModelParameter> parameters = modelParameterService.getListByModelId(id);
            mv.addObject("parameters", parameters);
        } catch (Exception e) {
            logger.error("get interface model error", e);
        }
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "loadObject", method = RequestMethod.POST)
    public Object loadObject(Long id) {
        Model object = null;
        try {
            object = modelService.getObject(id);
        } catch (Exception e) {
            logger.error("load model object error", e);
        }
        return object;
    }

    @ResponseBody
    @RequestMapping(value = "loadDetails", method = RequestMethod.POST)
    public Object loadDetails(Long id) {
        Model object = null;
        try {
            object = modelService.getObject(id);
            List list = modelParameterService.getListByModelId(id);
            object.setParameterList(list);
        } catch (Exception e) {
            logger.error("load model object error", e);
        }
        return object;
    }

    @ResponseBody
    @RequestMapping(value = "loadExampleJson", method = RequestMethod.POST)
    public Object loadExampleJson(HttpServletRequest request) {
        JSONObject result = new JSONObject();
        try {
            String idStr = request.getParameter("id");
            String code = request.getParameter("code");
            Model model = null;
            if (StringUtils.isNotBlank(idStr)) {
                model = modelService.getObject(Long.valueOf(idStr));
            } else if (StringUtils.isNotBlank(code)) {
                model = modelService.getObjectByCode(code);
            }
            if (null != model) {
                List list = modelParameterService.getListByModelId(model.getId());
                model.setParameterList(list);
                result.put("id", model.getId());
                result.put("code", model.getCode());
                result.put("example", model.getExampleJsonString());
            }
        } catch (Exception e) {
            logger.error("load model loadExampleJson error", e);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "write", method = RequestMethod.POST)
    public String write(Long id, Long frameId) {
        try {
            Model model = modelService.getObject(id);
            List<TemplateConfig> templateList = templateService.getListByFrameId(frameId);
            if (null != model && StringUtils.isNotBlank(model.getCode()) && null != templateList && templateList.size() > 0) {
                List parameters = modelParameterService.getListByModelId(model.getId());
                model.setParameterList(parameters);
                FreeMarkerUtils.getInstance().writeModel(model.getApplication(),model, templateList);
                return RESULT_SUCCESS;
            }
        } catch (Exception e) {
            logger.error("write model to code error", e);
            return e.getMessage();
        }
        return RESULT_ERROR;
    }

}