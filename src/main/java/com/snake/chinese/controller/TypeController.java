package com.snake.chinese.controller;

import com.base.Constants;
import com.snake.system.controller.BasicController;
import com.base.exception.ServiceException;
import com.base.util.Condition;
import com.base.util.Criteria;
import com.base.util.DateTimeUtils;
import com.base.util.SimpleCriteria;
import com.snake.chinese.model.Type;
import com.snake.chinese.service.ITypeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by wen on 2015/5/1.
 */
@Controller
@RequestMapping("/book/word/type/")
public class TypeController extends BasicController {

    @Resource(name = "typeService")
    private ITypeService typeService;

    @RequestMapping(value = "page")
    public ModelAndView page(Integer pageSize,Integer pageNo,HttpServletRequest request){
        ModelAndView mv = new ModelAndView("/chinese/word/type/list");
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        Criteria c = new SimpleCriteria();
        if(StringUtils.isNotEmpty(name)){
            c.addCondition(0,new Condition("name_","like","%"+name+"%"));
            mv.addObject("name",name);
        }
        if(StringUtils.isNotEmpty(type)){
            c.addCondition(0,new Condition("type_","=",type));
            mv.addObject("type", type);
        }
        try {
            c.setPageNo(pageNo == null?1:pageNo);
            c.setFetchSize(pageSize == null?Constants.PAGE_SIZE:pageSize);
            Page<Type> page = typeService.getList(c);
            mv.addObject("page",page);
        }catch (ServiceException e){
            logger.error("获取词组类型信息失败",e);
        }
        return mv;
    }

    @RequestMapping(value = "toAdd")
    public ModelAndView toAdd(){
        ModelAndView mv = new ModelAndView("/chinese/word/type/add");
        return mv;
    }

    @RequestMapping(value = "add")
    public RedirectView add(Type type){
        RedirectView rv = new RedirectView("page");
        try{
            type.setCreatedTime(DateTimeUtils.getNowDateTime());
            typeService.create(type);
        }catch (ServiceException e){
            logger.error("add book word type failed",e);
        }
        return rv;
    }

    @RequestMapping(value = "toEdit")
    public ModelAndView toEdit(Long id){
        ModelAndView mv = new ModelAndView("/chinese/word/type/edit");
        try {
            Type type = typeService.getObject(id);
            mv.addObject("obj",type);
        }catch (ServiceException e){
            logger.error("get type failed",e);
        }
        return mv;
    }

    @RequestMapping(value = "edit")
    public RedirectView edit(Type type){
        RedirectView rv = new RedirectView("page");
        try{
            typeService.update(type);
        }catch (ServiceException e){
            logger.error("edit book word type failed",e);
        }
        return rv;
    }

    @RequestMapping(value = "delete")
    public RedirectView delete(Long id){
        RedirectView rv = new RedirectView("page");
        try{
            typeService.delete(id);
        }catch (ServiceException e){
            logger.error("add book word type failed",e);
        }
        return rv;
    }

    @RequestMapping(value = "details")
    public ModelAndView details(Integer pageSize,Integer start,HttpServletRequest request){
        ModelAndView mv = new ModelAndView("/chinese/word/type/details");
        String name = request.getParameter("name");
        Criteria c = new SimpleCriteria();
        if(StringUtils.isNotEmpty(name)){
            c.addCondition(0,new Condition("name_","like","%"+name+"%"));
        }
        try {
            c.setFetchSize(pageSize == null ? 5 : pageSize);
            c.setStart(start == null ? 0 : start);
            Page<Type> page = typeService.getList(c);
            mv.addObject("page",page);
        }catch (ServiceException e){
            logger.error("获取词组信息失败",e);
        }
        return mv;
    }

}
