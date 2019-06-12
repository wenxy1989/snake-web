package com.snake.inter.controller;

import com.snake.system.controller.BasicController;
import com.base.util.Condition;
import com.base.util.Criteria;
import com.base.util.DateTimeUtils;
import com.base.util.SimpleCriteria;
import com.snake.inter.model.*;
import com.snake.inter.service.IModelParameterService;
import com.snake.inter.service.IParameterService;
import com.snake.inter.service.IUploadService;
import com.snake.inter.service.IUrlService;
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
import java.util.List;

@Controller
@RequestMapping("/inter/upload/")
public class UploadController extends BasicController {

    @Resource(name = "uploadService")
    private IUploadService uploadService;

    @Resource(name = "urlService")
    private IUrlService urlService;

    @Resource(name = "parameterService")
    private IParameterService parameterService;
    @Resource(name = "modelParameterService")
    private IModelParameterService modelParameterService;

    @RequestMapping(value = "{urlId}/page", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView page(@PathVariable Long urlId, Integer pageNo, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/inter/upload/list");
        responseTip(mv, request);
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        String type = request.getParameter("type");
        Criteria cri = new SimpleCriteria();
        cri.addCondition(0, new Condition("url_id", "=", urlId));
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
            Url url = urlService.getObject(urlId);
            mv.addObject("url", url);
            Page page = uploadService.getList(cri);
            mv.addObject("page", page);
        } catch (Exception e) {
            logger.error("find interface upload page error", e);
        }
        mv.addObject("urlId", urlId);
        return mv;
    }

    @RequestMapping(value = "{urlId}/toAdd")
    public ModelAndView toAdd(@PathVariable Long urlId) {
        ModelAndView mv = new ModelAndView("/inter/upload/add");
        mv.addObject("urlId", urlId);
        return mv;
    }

    @RequestMapping(value = "{urlId}/add", method = RequestMethod.POST)
    public RedirectView add(@PathVariable Long urlId,Upload upload, HttpServletRequest request) {
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try {
            if (!StringUtils.isBlank(upload.getCode()) && !StringUtils.isBlank(upload.getName())) {
                uploadService.create(upload);
                Parameter obj = upload.clone();
                Parameter parameter = parameterService.findOne(obj);
                if (null == parameter) {
                    obj.setCreatorId(getLoginUserId(request));
                    obj.setCreatedTime(DateTimeUtils.getNowDateTime());
                    parameterService.create(obj);
                }
                result = RESULT_ADD_SUCCESS;
            }
        } catch (Exception e) {
            logger.error("create interface upload error", e);
        }
        rv.addStaticAttribute("urlId", urlId);
        rv.addStaticAttribute(OPE_RESULT, result);
        return rv;
    }

    @RequestMapping(value = "{urlId}/toEdit", method = RequestMethod.GET)
    public ModelAndView toEdit(@PathVariable Long urlId,Long id) {
        ModelAndView mv = new ModelAndView("/inter/upload/edit");
        try {
            Upload upload = uploadService.getObject(id);
            mv.addObject("upload", upload);
        } catch (Exception e) {
            logger.error("find interface upload error.", e);
        }
        mv.addObject("urlId", urlId);
        return mv;
    }

    @RequestMapping(value = "{urlId}/edit", method = RequestMethod.POST)
    public RedirectView update(@PathVariable Long urlId,Upload upload) {
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try {
            if (StringUtils.isNotBlank(upload.getCode())) {
                uploadService.update(upload);
                result = RESULT_EDIT_SUCCESS;
            }
        } catch (Exception e) {
            logger.error("update interface upload error", e);
        }
        rv.addStaticAttribute(OPE_RESULT, result);
        rv.addStaticAttribute("urlId", urlId);
        return rv;
    }

    @RequestMapping(value = "{urlId}/delete")
    public RedirectView delete(@PathVariable Long urlId,Long id) {
        RedirectView rv = new RedirectView("page");
        String upload = RESULT_ERROR;
        try {
            uploadService.delete(id);
            upload = RESULT_DELETE_SUCCESS;
        } catch (Exception e) {
            logger.error("delete interface upload error", e);
        }
        rv.addStaticAttribute(OPE_RESULT, upload);
        rv.addStaticAttribute("urlId", urlId);
        return rv;
    }

    @ResponseBody
    @RequestMapping(value = "{urlId}/addParameter")
    public Object addParameter(@PathVariable Long urlId, Long parameterId) {
        String result = RESULT_ERROR;
        try {
            Parameter parameter = parameterService.getObject(parameterId);
            if (null != parameter) {
                Upload upload = parameter.clone(Upload.class);
                upload.setUrlId(urlId);
                uploadService.create(upload);
                result = RESULT_SUCCESS;
            }
        } catch (Exception e) {
            logger.error("add parameter to url upload error", e);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "{urlId}/importModelParameters")
    public Object importModelParameters(@PathVariable Long urlId, Long modelId) {
        String resultCode = RESULT_ERROR;
        try {
            List<ModelParameter> parameters = modelParameterService.getListByModelId(modelId);
            if (null != parameters && parameters.size() > 0) {
                for (ModelParameter parameter : parameters) {
                    Upload obj = parameter.clone(Upload.class);
                    obj.setUrlId(urlId);
                    obj.setAllowBlank(false);
                    uploadService.create(obj);
                }
                resultCode = RESULT_SUCCESS;
            }
        } catch (Exception e) {
            logger.error("add parameter to url result error", e);
        }
        return resultCode;
    }

}