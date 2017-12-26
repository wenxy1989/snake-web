package com.snake.chinese.controller;

import com.base.Constants;
import com.snake.system.controller.BasicController;
import com.base.exception.ServiceException;
import com.base.util.Condition;
import com.base.util.Criteria;
import com.base.util.DateTimeUtils;
import com.base.util.SimpleCriteria;
import com.snake.chinese.model.relation.Predicate;
import com.snake.chinese.service.IPredicateService;
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
@RequestMapping("/book/predicate/")
public class PredicateController extends BasicController {

    @Resource(name = "predicateService")
    private IPredicateService predicateService;

    @RequestMapping(value = "page")
    public ModelAndView page(Integer pageSize,Integer pageNo,HttpServletRequest request){
        ModelAndView mv = new ModelAndView("/chinese/predicate/list");
        String name = request.getParameter("name");
        String predicate = request.getParameter("predicate");
        Criteria c = new SimpleCriteria();
        if(StringUtils.isNotEmpty(name)){
            c.addCondition(0,new Condition("name_","like","%"+name+"%"));
            mv.addObject("name",name);
        }
        if(StringUtils.isNotEmpty(predicate)){
            c.addCondition(0,new Condition("predicate_","=",predicate));
            mv.addObject("predicate", predicate);
        }
        try {
            c.setPageNo(pageNo == null?1:pageNo);
            c.setFetchSize(pageSize == null?Constants.PAGE_SIZE:pageSize);
            Page<Predicate> page = predicateService.getList(c);
            mv.addObject("page",page);
        }catch (ServiceException e){
            logger.error("获取谓语动词信息失败",e);
        }
        return mv;
    }

    @RequestMapping(value = "toAdd")
    public ModelAndView toAdd(){
        ModelAndView mv = new ModelAndView("/chinese/predicate/add");
        return mv;
    }

    @RequestMapping(value = "add")
    public RedirectView add(Predicate predicate){
        RedirectView rv = new RedirectView("page");
        try{
            predicate.setCreatedTime(DateTimeUtils.getNowDateTime());
            predicateService.create(predicate);
        }catch (ServiceException e){
            logger.error("add book word predicate failed",e);
        }
        return rv;
    }

    @RequestMapping(value = "toEdit")
    public ModelAndView toEdit(Long id){
        ModelAndView mv = new ModelAndView("/chinese/predicate/edit");
        try {
            Predicate predicate = predicateService.getObject(id);
            mv.addObject("obj", predicate);
        }catch (ServiceException e){
            logger.error("get predicate failed",e);
        }
        return mv;
    }

    @RequestMapping(value = "edit")
    public RedirectView edit(Predicate predicate){
        RedirectView rv = new RedirectView("page");
        try{
            predicateService.update(predicate);
        }catch (ServiceException e){
            logger.error("edit book word predicate failed",e);
        }
        return rv;
    }

    @RequestMapping(value = "delete")
    public RedirectView delete(Long id){
        RedirectView rv = new RedirectView("page");
        try{
            predicateService.delete(id);
        }catch (ServiceException e){
            logger.error("add book word predicate failed",e);
        }
        return rv;
    }

}
