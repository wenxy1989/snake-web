package com.snake.inter.controller;

import com.base.Constants;
import com.snake.system.controller.BasicController;
import com.base.exception.ServiceException;
import com.snake.freemarker.FreeMarkerUtils;
import com.base.util.*;
import com.snake.inter.model.Group;
import com.snake.inter.model.Result;
import com.snake.inter.model.Upload;
import com.snake.inter.model.Url;
import com.snake.inter.service.IGroupService;
import com.snake.inter.service.IResultService;
import com.snake.inter.service.IUploadService;
import com.snake.inter.service.IUrlService;
import com.snake.system.model.User;
import com.snake.system.service.IParameterService;
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
@RequestMapping("/inter/url/")
public class UrlController extends BasicController {

    @Resource(name = "i_groupService")
    private IGroupService groupService;
    @Resource(name = "urlService")
    private IUrlService urlService;
    @Resource(name = "uploadService")
    private IUploadService uploadService;
    @Resource(name = "resultService")
    private IResultService resultService;

    @Resource(name = "sysParameterService")
    private IParameterService sysParameterService;

    @RequestMapping(value = "page", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView page(Integer pageNo, Integer size, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/inter/url/page");
        responseTip(mv, request);
        String groupId = request.getParameter("groupId");
        String name = request.getParameter("name");
        String url = request.getParameter("url");
        String type = request.getParameter("type");
        String status = request.getParameter("status");
        String mine = request.getParameter("mine");
        status = StringUtils.isNotBlank(status) ? status : "0";
        mine = StringUtils.isNotBlank(mine) ? mine : "0";
        Criteria cri = new SimpleCriteria();
        if (StringUtils.isNotBlank(groupId)) {
            cri.addCondition(0, new Condition("group_id", "=", Long.valueOf(groupId)));
            mv.addObject("groupId", groupId);
        }
        if (StringUtils.isNotBlank(name)) {
            cri.addCondition(0, new Condition("name_", "like", name + "%"));
            mv.addObject("name", name);
        }
        if (StringUtils.isNotBlank(url)) {
            cri.addCondition(0, new Condition("url_", "like", url + "%"));
            mv.addObject("url", url);
        }
        if (StringUtils.isNotBlank(type)) {
            cri.addCondition(0, new Condition("type_", "=", Integer.valueOf(type)));
            mv.addObject("type", type);
        }
        if (StringUtils.isNotBlank(status)) {
            cri.addCondition(0, new Condition("status_", "=", Integer.valueOf(status)));
            mv.addObject("status", status);
        }
        if ("1".equals(mine)) {
            Long userId = getLoginUserId(request);
            cri.addCondition(0, new Condition("creator_id", "=", userId));
        }
        mv.addObject("mine", mine);
        cri.setFetchSize(size == null ? Constants.PAGE_SIZE : size);
        cri.setPageNo(pageNo == null ? 1 : pageNo);
        try {
            Page page = urlService.getList(cri);
            mv.addObject("page", page);
        } catch (ServiceException e) {
            logger.error("find interface url page error", e);
        }
        return mv;
    }

    @RequestMapping(value = "{applicationId}/page", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView applicationPage(@PathVariable Long applicationId, Integer pageNo, Integer size, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/inter/url/list");
        responseTip(mv, request);
        String groupId = request.getParameter("groupId");
        String name = request.getParameter("name");
        String url = request.getParameter("url");
        String type = request.getParameter("type");
        String status = request.getParameter("status");
        String mine = request.getParameter("mine");
        status = StringUtils.isNotBlank(status) ? status : "0";
        mine = StringUtils.isNotBlank(mine) ? mine : "0";
        Criteria cri = new SimpleCriteria();
        cri.addCondition(0, new Condition("application_id", "=", applicationId));
        if (StringUtils.isNotBlank(groupId)) {
            cri.addCondition(0, new Condition("group_id", "=", Long.valueOf(groupId)));
            mv.addObject("groupId", groupId);
        }
        if (StringUtils.isNotBlank(name)) {
            cri.addCondition(0, new Condition("name_", "like", name + "%"));
            mv.addObject("name", name);
        }
        if (StringUtils.isNotBlank(url)) {
            cri.addCondition(0, new Condition("url_", "like", url + "%"));
            mv.addObject("url", url);
        }
        if (StringUtils.isNotBlank(type)) {
            cri.addCondition(0, new Condition("type_", "=", Integer.valueOf(type)));
            mv.addObject("type", type);
        }
        if (StringUtils.isNotBlank(status)) {
            cri.addCondition(0, new Condition("status_", "=", Integer.valueOf(status)));
            mv.addObject("status", status);
        }
        if ("1".equals(mine)) {
            Long userId = getLoginUserId(request);
            cri.addCondition(0, new Condition("creator_id", "=", userId));
        }
        mv.addObject("mine", mine);
        cri.setFetchSize(size == null ? Constants.PAGE_SIZE : size);
        cri.setPageNo(pageNo == null ? 1 : pageNo);
        cri.addOrder(0, "group_id asc");
        try {
            Page page = urlService.getList(cri);
            mv.addObject("page", page);
        } catch (ServiceException e) {
            logger.error("find interface url page error", e);
        }
        mv.addObject("applicationId", applicationId);
        return mv;
    }

    @RequestMapping(value = "{applicationId}/toAdd", method = RequestMethod.GET)
    public ModelAndView toAdd(@PathVariable Long applicationId, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/inter/url/add");
        mv.addObject("applicationId", applicationId);
        return mv;
    }

    @RequestMapping(value = "{applicationId}/add", method = RequestMethod.POST)
    public RedirectView add(@PathVariable Long applicationId, Url url, HttpServletRequest request) {
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try {
            if (StringUtils.isNotBlank(url.getUrl()) && StringUtils.isNotBlank(url.getName())) {
                Url object = urlService.getObjectByUrl(url.getUrl());
                if (object != null) {
                    result = RESULT_EXISTS;
                } else {
                    User loginUser = getLoginUser(request);
                    Long creatorId = loginUser.getId();
                    url.setCreatedTime(DateTimeUtils.getNowDateTime());
                    url.setCreatorId(creatorId);
                    url.setPosition(0);//header
                    url.setResultType(3);//json
                    url.setStatus(0);//草稿
                    urlService.create(url);
                    result = RESULT_ADD_SUCCESS;
                }
            }
        } catch (Exception e) {
            logger.error("create interface url error", e);
        }
        rv.addStaticAttribute("applicationId", applicationId);
        rv.addStaticAttribute(OPE_RESULT, result);
        return rv;
    }

    @RequestMapping(value = "{applicationId}/toSubmit", method = RequestMethod.GET)
    public ModelAndView toSubmit(@PathVariable Long applicationId, Long id, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/inter/url/submit");
        try {
            Url url = urlService.getObject(id);
            if (null != url) {
                if (StringUtils.isBlank(url.getUploadExample())) {
                    List uploadList = uploadService.getListByUrlId(url.getId());
                    url.setUploadParameters(uploadList);
                    url.setUploadExample(url.getUploadJsonString());
                }
                if (StringUtils.isBlank(url.getResultExample())) {
                    List resultList = resultService.getListByUrlId(url.getId());
                    url.setResultParameters(resultList);
                    url.setResultExample(url.getResultJsonString());
                }
                mv.addObject("url", url);
            }
        } catch (ServiceException e) {
            logger.error("find interface url error.", e);
        }
        mv.addObject("applicationId", applicationId);
        return mv;
    }

    @RequestMapping(value = "{applicationId}/toEdit", method = RequestMethod.GET)
    public ModelAndView toEdit(@PathVariable Long applicationId, Long id, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/inter/url/edit");
        try {
            Url url = urlService.getObject(id);
            if (null != url) {
                mv.addObject("url", url);
            }
        } catch (ServiceException e) {
            logger.error("find interface url error.", e);
        }
        mv.addObject("applicationId", applicationId);
        return mv;
    }

    @RequestMapping(value = "{applicationId}/edit", method = RequestMethod.POST)
    public RedirectView edit(@PathVariable Long applicationId, Url url, HttpServletRequest request) {
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try {
            if (StringUtils.isNotBlank(url.getUrl())) {
                urlService.update(url);
                result = RESULT_EDIT_SUCCESS;
            }
        } catch (Exception e) {
            logger.error("update interface url error", e);
        }
        rv.addStaticAttribute(OPE_RESULT, result);
        rv.addStaticAttribute("applicationId", applicationId);
        return rv;
    }

    @RequestMapping(value = "{applicationId}/delete", method = RequestMethod.GET)
    public RedirectView delete(@PathVariable Long applicationId, Long id, HttpServletRequest request) {
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try {
            urlService.delete(id);
            result = RESULT_DELETE_SUCCESS;
        } catch (Exception e) {
            logger.error("delete interface url error", e);
        }
        rv.addStaticAttribute(OPE_RESULT, result);
        rv.addStaticAttribute("applicationId", applicationId);
        return rv;
    }

    @RequestMapping(value = "{applicationId}/submit", method = RequestMethod.GET)
    public RedirectView submit(@PathVariable Long applicationId, Url url) {
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try {
            url.setStatus(1);
            urlService.update(url);
            result = RESULT_EDIT_SUCCESS;
        } catch (Exception e) {
            logger.error("submit interface url error", e);
        }
        rv.addStaticAttribute(OPE_RESULT, result);
        rv.addStaticAttribute("applicationId", applicationId);
        return rv;
    }

    @RequestMapping(value = "{applicationId}/publish", method = RequestMethod.GET)
    public RedirectView publish(@PathVariable Long applicationId, Long id, HttpServletRequest request) {
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try {
            Url url = urlService.getObject(id);
            if (null != url && url.getStatus() == 1) {//status is submit
                url.setStatus(3);
                urlService.update(url);
                result = RESULT_EDIT_SUCCESS;
            }
        } catch (Exception e) {
            logger.error("publish interface url error", e);
        }
        rv.addStaticAttribute(OPE_RESULT, result);
        rv.addStaticAttribute("applicationId", applicationId);
        return rv;
    }

    @RequestMapping(value = "{applicationId}/return", method = RequestMethod.GET)
    public RedirectView doReturn(@PathVariable Long applicationId, Long id, HttpServletRequest request) {
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try {
            Url url = urlService.getObject(id);
            if (null != url && url.getStatus() == 1) {//status is submit
                url.setStatus(2);
                urlService.update(url);
                result = RESULT_EDIT_SUCCESS;
            }
        } catch (Exception e) {
            logger.error("delete interface url error", e);
        }
        rv.addStaticAttribute(OPE_RESULT, result);
        rv.addStaticAttribute("applicationId", applicationId);
        return rv;
    }

    @RequestMapping(value = "{applicationId}/submitted", method = RequestMethod.GET)
    public ModelAndView submitted(@PathVariable Long applicationId, Long id, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/inter/url/submitted");
        try {
            Integer status = 1;//submitted
            Criteria criteria = new SimpleCriteria();
            criteria.addCondition(0, new Condition("application_id", "=", applicationId));
            criteria.addCondition(0, new Condition("status_", "=", status));
            List<Url> urlList = urlService.getPageList(criteria);
            if (null != urlList && urlList.size() > 0) {
                for (Url url : urlList) {
                    List uploads = uploadService.getListByUrlId(url.getId());
                    List results = resultService.getListByUrlId(url.getId());
                    url.setUploadParameters(uploads);
                    url.setResultParameters(results);
                }
                mv.addObject("urlList", urlList);
            }
        } catch (Exception e) {
            logger.error("delete interface url error", e);
        }
        return mv;
    }

    @RequestMapping(value = "{applicationId}/details", method = RequestMethod.GET)
    public ModelAndView details(@PathVariable Long applicationId, Long id) {
        ModelAndView mv = new ModelAndView("/inter/url/details");
        try {
            Url url = urlService.getObject(id);
            List<Upload> uploads = uploadService.getListByUrlId(id);
            List<Result> results = resultService.getListByUrlId(id);
            mv.addObject("url", url);
            mv.addObject("uploads", uploads);
            mv.addObject("results", results);
        } catch (ServiceException e) {
            logger.error("find interface url error.", e);
        }
        mv.addObject("applicationId", applicationId);
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "{applicationId}/writeCode")
    public Object writeCode(@PathVariable Long applicationId) {
        String result = RESULT_ERROR;
        try {
//        String templatePath = sysParameterService.getObjectByCode("school-book-template-inter-folder").getStringValue();
            String outputPath = sysParameterService.getObjectByCode("school-book-output-base-path").getStringValue();
            FreeMarkerUtils freeMarker = FreeMarkerUtils.getNewInstance(outputPath);
            List<Group> groupList = groupService.getListByApplicationId(applicationId);
            if (null != groupList && groupList.size() > 0) {
                for (Group group : groupList) {
                    List<Url> urlList = urlService.getListByGroupId(group.getId());
                    if (null != urlList && urlList.size() > 0) {
                        for (Url url : urlList) {
                            List uploads = uploadService.getListByUrlId(url.getId());
                            List results = resultService.getListByUrlId(url.getId());
                            url.setUploadParameters(uploads);
                            url.setResultParameters(results);
                        }
                    }
                    group.setUrlList(urlList);
                    freeMarker.writeInter(group);
                }
            }
            result = RESULT_SUCCESS;
        } catch (ServiceException e) {
            logger.error("write interface url error.", e);
        }
        return result;
    }

}