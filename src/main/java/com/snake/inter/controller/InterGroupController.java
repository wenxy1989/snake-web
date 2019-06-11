package com.snake.inter.controller;

import com.base.Constants;
import com.snake.system.controller.BasicController;
import com.base.exception.ServiceException;
import com.snake.freemarker.FreeMarkerUtils;
import com.base.util.*;
import com.snake.inter.model.Group;
import com.snake.inter.model.Url;
import com.snake.inter.service.IGroupService;
import com.snake.inter.service.IResultService;
import com.snake.inter.service.IUploadService;
import com.snake.inter.service.IUrlService;
import com.snake.system.model.User;
import com.snake.system.service.IParameterService;
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
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/inter/group/")
public class InterGroupController extends BasicController {

    @Resource(name = "groupService")
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
        ModelAndView mv = new ModelAndView("/inter/group/list");
        responseTip(mv, request);
        String name = request.getParameter("name");
        String group = request.getParameter("group");
        String status = request.getParameter("status");
        String mine = request.getParameter("mine");
        status = org.apache.commons.lang.StringUtils.isNotBlank(status) ? status : "0";
        mine = org.apache.commons.lang.StringUtils.isNotBlank(mine) ? mine : "0";
        Criteria cri = new SimpleCriteria();
        if (org.apache.commons.lang.StringUtils.isNotBlank(name)) {
            cri.addCondition(0, new Condition("name_", "like", name + "%"));
            mv.addObject("name", name);
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(group)) {
            cri.addCondition(0, new Condition("group_", "like", group + "%"));
            mv.addObject("group", group);
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
            Page page = groupService.getList(cri);
            mv.addObject("page", page);
        } catch (ServiceException e) {
            logger.error("find interface group page error", e);
        }
        return mv;
    }

    @RequestMapping(value = "{applicationId}/page", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView applicationPage(@PathVariable Long applicationId,Integer pageNo, Integer size, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/inter/group/list");
        responseTip(mv, request);
        String name = request.getParameter("name");
        String group = request.getParameter("group");
        String status = request.getParameter("status");
        String mine = request.getParameter("mine");
        status = org.apache.commons.lang.StringUtils.isNotBlank(status) ? status : "0";
        mine = org.apache.commons.lang.StringUtils.isNotBlank(mine) ? mine : "0";
        Criteria cri = new SimpleCriteria();
        cri.addCondition(0, new Condition("application_id", "=", applicationId));
        mv.addObject("applicationId", applicationId);
        if (org.apache.commons.lang.StringUtils.isNotBlank(name)) {
            cri.addCondition(0, new Condition("name_", "like", name + "%"));
            mv.addObject("name", name);
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(group)) {
            cri.addCondition(0, new Condition("group_", "like", group + "%"));
            mv.addObject("group", group);
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
            Page page = groupService.getList(cri);
            mv.addObject("page", page);
        } catch (ServiceException e) {
            logger.error("find interface group page error", e);
        }
        return mv;
    }

    @RequestMapping(value = "{applicationId}/toAdd", method = RequestMethod.GET)
    public ModelAndView toAdd(@PathVariable Long applicationId,HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/inter/group/add");
        mv.addObject("applicationId", applicationId);
        return mv;
    }

    @RequestMapping(value = "{applicationId}/add", method = RequestMethod.POST)
    public RedirectView add(@PathVariable Long applicationId,Group group, HttpServletRequest request) {
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try {
            User loginUser = getLoginUser(request);
            Long creatorId = loginUser.getId();
            group.setCreatedTime(DateTimeUtils.getNowDateTime());
            group.setCreatorId(creatorId);
            group.setStatus(0);//草稿
            groupService.create(group);
            result = RESULT_ADD_SUCCESS;
        } catch (ServiceException e) {
            logger.error("create interface group error", e);
        }
        rv.addStaticAttribute("applicationId",applicationId);
        rv.addStaticAttribute(OPE_RESULT, result);
        return rv;
    }

    @RequestMapping(value = "{applicationId}/toEdit", method = RequestMethod.GET)
    public ModelAndView toEdit(@PathVariable Long applicationId,Long id) {
        ModelAndView mv = new ModelAndView("/inter/group/edit");
        mv.addObject("applicationId",applicationId);
        try {
            Group group = groupService.getObject(id);
            mv.addObject("group", group);
        } catch (ServiceException e) {
            logger.error("find interface group error.", e);
        }
        return mv;
    }

    @RequestMapping(value = "{applicationId}/edit", method = RequestMethod.POST)
    public RedirectView update(@PathVariable Long applicationId,Group group) {
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try {
            groupService.update(group);
            result = RESULT_EDIT_SUCCESS;
        } catch (Exception e) {
            logger.error("update interface group error", e);
        }
        rv.addStaticAttribute("applicationId",applicationId);
        rv.addStaticAttribute(OPE_RESULT, result);
        return rv;
    }

    @RequestMapping(value = "{applicationId}/delete", method = RequestMethod.GET)
    public RedirectView delete(@PathVariable Long applicationId,Long id) {
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try {
            groupService.delete(id);
            result = RESULT_DELETE_SUCCESS;
        } catch (Exception e) {
            logger.error("delete interface group error", e);
        }
        rv.addStaticAttribute("applicationId",applicationId);
        rv.addStaticAttribute(OPE_RESULT, result);
        return rv;
    }

    @RequestMapping(value = "details", method = RequestMethod.GET)
    public ModelAndView details(Long id) {
        ModelAndView mv = new ModelAndView("/inter/group/details");
        try {
            Group group = groupService.getObject(id);

            List<Url> urlList = urlService.getListByGroupId(group.getId());
            if(null != urlList && urlList.size() > 0) {
                for(Url url : urlList) {
                    List uploads = uploadService.getListByUrlId(url.getId());
                    List results = resultService.getListByUrlId(url.getId());
                    url.setUploadParameters(uploads);
                    url.setResultParameters(results);
                }
            }
            mv.addObject("group", group);
            mv.addObject("urlList", urlList);
            mv.addObject("now", new Date());
        } catch (ServiceException e) {
            logger.error("find interface group details error.", e);
        }
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "writeCode", method = RequestMethod.POST)
    public Object writeCode(Long id) {
        String result = RESULT_ERROR;
        try {
            String outputPath = sysParameterService.getObjectByCode("school-book-output-base-path").getStringValue();
            FreeMarkerUtils freeMarker = FreeMarkerUtils.getNewInstance(outputPath);
            Group group = groupService.getObject(id);
            List<Url> urlList = urlService.getListByGroupId(group.getId());
            if(null != urlList && urlList.size() > 0) {
                for(Url url : urlList) {
                    List uploads = uploadService.getListByUrlId(url.getId());
                    List results = resultService.getListByUrlId(url.getId());
                    url.setUploadParameters(uploads);
                    url.setResultParameters(results);
                }
            }
            group.setUrlList(urlList);
            freeMarker.writeInter(group);
            result = RESULT_SUCCESS;
        } catch (ServiceException e) {
            logger.error("write interface group inters error.", e);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "loadObject", method = RequestMethod.POST)
    public Object loadObject(Long id){
        Group object = null;
        try{
            object = groupService.getObject(id);
        }catch (ServiceException e){
            logger.error("load group object error",e);
        }
        return object;
    }

}