package com.snake.system.controller;

import com.base.Constants;
import com.base.exception.ServiceException;
import com.base.util.Condition;
import com.base.util.Criteria;
import com.base.util.DateTimeUtils;
import com.base.util.SimpleCriteria;
import com.snake.system.model.Index;
import com.snake.system.service.IFunctionService;
import com.snake.system.service.IIndexService;
import com.snake.system.service.IRoleService;
import com.snake.system.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/sys/index/")
public class IndexController extends BasicController {

    @Resource(name = "indexService")
    private IIndexService indexService;

    @Resource(name = "userService")
    private IUserService userService;

    @Resource(name = "roleService")
    private IRoleService roleService;

    @Resource(name = "functionService")
    private IFunctionService functionService;

    @RequestMapping(value = "page")
    public ModelAndView page(Integer pageNo, Integer size, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/system/index/list");
        String ope_result = request.getParameter(OPE_RESULT);
        if (StringUtils.isNotBlank(ope_result)) {
            mv.addObject(OPE_RESULT, ope_result + "," + System.currentTimeMillis());
        }
        Criteria cri = new SimpleCriteria();
        cri.setPageNo(pageNo == null ? 1 : pageNo);
        cri.setFetchSize(size == null ? Constants.PAGE_SIZE : size);
        try {
            Page page = indexService.getList(cri);
            mv.addObject("page", page);
        } catch (ServiceException e) {
            logger.error("获取首页设置信息失败", e);
        }
        return mv;
    }

    @RequestMapping(value = "delete")
    public RedirectView delete(Long id){
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try {
            indexService.delete(id);
            result = RESULT_DELETE_SUCCESS;
        } catch (ServiceException e) {
            logger.error("delete from index error", e);
        }
        rv.addStaticAttribute(OPE_RESULT,result);
        return rv;
    }

    @RequestMapping(value = "user/page")
    public ModelAndView userPage(Integer pageNo, Integer size, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/system/index/select_user");
        String name = request.getParameter("user");
        Criteria cri = new SimpleCriteria();
        if (StringUtils.isNotEmpty(name)) {
            cri.addCondition(0, new Condition("user_name", "like", "%" + name + "%"));
            mv.addObject("name", name);
        }
        cri.setPageNo(pageNo == null ? 1 : pageNo);
        cri.setFetchSize(size == null ? Constants.PAGE_SIZE : size);
        try {
            Page page = userService.getList(cri);
            mv.addObject("page", page);
            mv.addObject("type",Constants.INDEX_TYPE_USER);
        } catch (ServiceException e) {
            logger.error("获取用户信息失败", e);
        }
        return mv;
    }

    @RequestMapping(value = "role/page")
    public ModelAndView rolePage(Integer pageNo, Integer size, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/system/index/select_role");
        Criteria cri = new SimpleCriteria();
        cri.setPageNo(pageNo == null ? 1 : pageNo);
        cri.setFetchSize(size == null ? Constants.PAGE_SIZE : size);
        try {
            Page page = roleService.getList(cri);
            mv.addObject("page", page);
            mv.addObject("type",Constants.INDEX_TYPE_ROLE);
        } catch (ServiceException e) {
            logger.error("获取角色信息失败", e);
        }
        return mv;
    }

    @RequestMapping(value = "function/page")
    public ModelAndView functionPage(Integer pageNo, Integer size, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/system/index/select_function");
        String name = request.getParameter("name");
        String objectId = request.getParameter("objectId");
        String type = request.getParameter("type");
        mv.addObject("objectId", objectId);
        mv.addObject("type", type);
        Criteria cri = new SimpleCriteria();
        if (StringUtils.isNotEmpty(name)) {
            cri.addCondition(0, new Condition("name_", "like", "%" + name + "%"));
            mv.addObject("name", name);
        }
        cri.setPageNo(pageNo == null ? 1 : pageNo);
        cri.setFetchSize(size == null ? Constants.PAGE_SIZE : size);
        try {
            Page page = functionService.getList(cri);
            mv.addObject("page", page);
        } catch (ServiceException e) {
            logger.error("获取角色信息失败", e);
        }
        return mv;
    }

    @RequestMapping(value = "setIndex")
    public RedirectView setIndex(Index index, HttpServletRequest request) {
        RedirectView rv = new RedirectView("page");
        try {
            if (null != index) {
                Long objectId = index.getObjectId();
                Index object = null;
                if (index.getType() == Constants.INDEX_TYPE_USER) {
                    object = indexService.getObjectByUserId(objectId);
                } else if (index.getType() == Constants.INDEX_TYPE_ROLE) {
                    object = indexService.getObjectByRoleId(objectId);
                }
                if (null == object) {
                    index.setCreatorId(getLoginUserId(request));
                    index.setCreatedTime(DateTimeUtils.getNowDateTime());
                    indexService.create(index);
                } else {
                    object.setUrl(index.getUrl());
                    object.setName(index.getName());
                    indexService.update(object);
                }
            }
        } catch (ServiceException e) {
            logger.error("设置首页失败", e);
        }
        return rv;
    }

}