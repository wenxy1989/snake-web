package com.snake.template.controller;

import com.base.Constants;
import com.base.exception.ServiceException;
import com.base.util.Condition;
import com.base.util.Criteria;
import com.base.util.DateTimeUtils;
import com.base.util.SimpleCriteria;
import com.snake.template.model.Group;
import com.snake.system.controller.BasicController;
import com.snake.system.model.User;
import com.snake.template.service.IGroupService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/template/group/")
public class TGroupController extends BasicController {

    @Resource(name = "t_groupService")
    private IGroupService groupService;

    @RequestMapping(value = "page", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView page(Integer pageNo, Integer size, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/template/group/list");
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

    @RequestMapping(value = "toAdd", method = RequestMethod.GET)
    public ModelAndView toAdd(@PathVariable Long applicationId,HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/template/group/add");
        mv.addObject("applicationId", applicationId);
        return mv;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public RedirectView add(@PathVariable Long applicationId,Group group, HttpServletRequest request) {
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try {
            User loginUser = getLoginUser(request);
            Long creatorId = loginUser.getId();
            group.setCreatedTime(DateTimeUtils.getNowDateTime());
            group.setCreatorId(creatorId);
            groupService.create(group);
            result = RESULT_ADD_SUCCESS;
        } catch (ServiceException e) {
            logger.error("create interface group error", e);
        }
        rv.addStaticAttribute("applicationId",applicationId);
        rv.addStaticAttribute(OPE_RESULT, result);
        return rv;
    }

    @RequestMapping(value = "toEdit", method = RequestMethod.GET)
    public ModelAndView toEdit(@PathVariable Long applicationId,Long id) {
        ModelAndView mv = new ModelAndView("/template/group/edit");
        mv.addObject("applicationId",applicationId);
        try {
            Group group = groupService.getObject(id);
            mv.addObject("group", group);
        } catch (ServiceException e) {
            logger.error("find interface group error.", e);
        }
        return mv;
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
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

    @RequestMapping(value = "delete", method = RequestMethod.GET)
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

}