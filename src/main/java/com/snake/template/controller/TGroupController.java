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
        String mine = request.getParameter("mine");
        Criteria cri = new SimpleCriteria();
        if (org.apache.commons.lang.StringUtils.isNotBlank(name)) {
            cri.addCondition(0, new Condition("name_", "like", name + "%"));
            mv.addObject("name", name);
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
    public ModelAndView toAdd(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/template/group/add");
        return mv;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public RedirectView add(Group group, HttpServletRequest request) {
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
        rv.addStaticAttribute(OPE_RESULT, result);
        return rv;
    }

    @RequestMapping(value = "toEdit", method = RequestMethod.GET)
    public ModelAndView toEdit(Long id) {
        ModelAndView mv = new ModelAndView("/template/group/edit");
        try {
            Group group = groupService.getObject(id);
            mv.addObject("group", group);
        } catch (ServiceException e) {
            logger.error("find interface group error.", e);
        }
        return mv;
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public RedirectView update(Group group) {
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try {
            groupService.update(group);
            result = RESULT_EDIT_SUCCESS;
        } catch (Exception e) {
            logger.error("update interface group error", e);
        }
        rv.addStaticAttribute(OPE_RESULT, result);
        return rv;
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public RedirectView delete(Long id) {
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try {
            groupService.delete(id);
            result = RESULT_DELETE_SUCCESS;
        } catch (Exception e) {
            logger.error("delete interface group error", e);
        }
        rv.addStaticAttribute(OPE_RESULT, result);
        return rv;
    }

}