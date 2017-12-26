package com.snake.chinese.controller;

import com.base.Constants;
import com.snake.system.controller.BasicController;
import com.base.exception.ServiceException;
import com.base.util.Condition;
import com.base.util.Criteria;
import com.base.util.DateTimeUtils;
import com.base.util.SimpleCriteria;
import com.snake.chinese.model.Element;
import com.snake.chinese.service.IElementService;
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
@RequestMapping("/book/element/")
public class ElementController extends BasicController {

    @Resource(name = "elementService")
    private IElementService elementService;

    @RequestMapping(value = "page")
    public ModelAndView page(Integer pageSize,Integer pageNo,HttpServletRequest request){
        ModelAndView mv = new ModelAndView("/chinese/element/list");
        String name = request.getParameter("name");
        String element = request.getParameter("element");
        Criteria c = new SimpleCriteria();
        if(StringUtils.isNotEmpty(name)){
            c.addCondition(0,new Condition("name_","like","%"+name+"%"));
            mv.addObject("name",name);
        }
        if(StringUtils.isNotEmpty(element)){
            c.addCondition(0,new Condition("element_","=",element));
            mv.addObject("element", element);
        }
        try {
            c.setPageNo(pageNo == null?1:pageNo);
            c.setFetchSize(pageSize == null?Constants.PAGE_SIZE:pageSize);
            Page<Element> page = elementService.getList(c);
            mv.addObject("page",page);
        }catch (ServiceException e){
            logger.error("获取语句元素信息失败",e);
        }
        return mv;
    }

    @RequestMapping(value = "toAdd")
    public ModelAndView toAdd(){
        ModelAndView mv = new ModelAndView("/chinese/element/add");
        return mv;
    }

    @RequestMapping(value = "add")
    public RedirectView add(Element element){
        RedirectView rv = new RedirectView("page");
        try{
            element.setCreatedTime(DateTimeUtils.getNowDateTime());
            elementService.create(element);
        }catch (ServiceException e){
            logger.error("add book word element failed",e);
        }
        return rv;
    }

    @RequestMapping(value = "toEdit")
    public ModelAndView toEdit(Long id){
        ModelAndView mv = new ModelAndView("/chinese/element/edit");
        try {
            Element element = elementService.getObject(id);
            mv.addObject("obj",element);
        }catch (ServiceException e){
            logger.error("get element failed",e);
        }
        return mv;
    }

    @RequestMapping(value = "edit")
    public RedirectView edit(Element element){
        RedirectView rv = new RedirectView("page");
        try{
            elementService.update(element);
        }catch (ServiceException e){
            logger.error("edit book word element failed",e);
        }
        return rv;
    }

    @RequestMapping(value = "delete")
    public RedirectView delete(Long id){
        RedirectView rv = new RedirectView("page");
        try{
            elementService.delete(id);
        }catch (ServiceException e){
            logger.error("add book word element failed",e);
        }
        return rv;
    }

}
