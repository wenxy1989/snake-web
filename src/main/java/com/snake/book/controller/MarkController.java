package com.snake.book.controller;

import com.base.Constants;
import com.snake.system.controller.BasicController;
import com.base.exception.ServiceException;
import com.base.util.Condition;
import com.base.util.Criteria;
import com.base.util.DateTimeUtils;
import com.base.util.SimpleCriteria;
import com.snake.book.model.Mark;
import com.snake.book.service.IMarkService;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by wen on 2015/5/1.
 */
@Controller
@RequestMapping("/book/mark/")
public class MarkController extends BasicController {

    @Resource(name = "markService")
    private IMarkService markService;

    @RequestMapping(value = "page")
    public ModelAndView page(Integer pageSize,Integer pageNo,HttpServletRequest request){
        ModelAndView mv = new ModelAndView("/book/mark/list");
        String name = request.getParameter("name");
        String mark = request.getParameter("mark");
        Criteria c = new SimpleCriteria();
        if(StringUtils.isNotEmpty(name)){
            c.addCondition(0,new Condition("name_","like","%"+name+"%"));
            mv.addObject("name",name);
        }
        if(StringUtils.isNotEmpty(mark)){
            c.addCondition(0,new Condition("mark_","=",mark));
            mv.addObject("mark", mark);
        }
        try {
            c.setPageNo(pageNo == null?1:pageNo);
            c.setFetchSize(pageSize == null?Constants.PAGE_SIZE:pageSize);
            Page<Mark> page = markService.getList(c);
            mv.addObject("page",page);
        }catch (ServiceException e){
            logger.error("获取书签信息失败",e);
        }
        return mv;
    }

    @RequestMapping(value = "toAdd")
    public ModelAndView toAdd(){
        ModelAndView mv = new ModelAndView("/book/mark/add");
        return mv;
    }

    @RequestMapping(value = "add")
    public RedirectView add(Mark mark){
        RedirectView rv = new RedirectView("page");
        try{
            mark.setCreatedTime(DateTimeUtils.getNowDateTime());
            markService.create(mark);
        }catch (ServiceException e){
            logger.error("add book word mark failed",e);
        }
        return rv;
    }

    @RequestMapping(value = "toEdit")
    public ModelAndView toEdit(Long id){
        ModelAndView mv = new ModelAndView("/book/mark/edit");
        try {
            Mark mark = markService.getObject(id);
            mv.addObject("obj", mark);
        }catch (ServiceException e){
            logger.error("get mark failed",e);
        }
        return mv;
    }

    @RequestMapping(value = "edit")
    public RedirectView edit(Mark mark){
        RedirectView rv = new RedirectView("page");
        try{
            markService.update(mark);
        }catch (ServiceException e){
            logger.error("edit book word mark failed",e);
        }
        return rv;
    }

    @RequestMapping(value = "delete")
    public RedirectView delete(Long id){
        RedirectView rv = new RedirectView("page");
        try{
            markService.delete(id);
        }catch (ServiceException e){
            logger.error("add book word mark failed",e);
        }
        return rv;
    }

    @ResponseBody
    @RequestMapping(value = "create")
    public Object create(Mark mark,HttpServletRequest request){
        String result = RESULT_ERROR;
        try{
            mark.setCreatorId(getLoginUserId(request));
            mark.setCreatedTime(DateTimeUtils.getNowDateTime());
            markService.create(mark);
            result = RESULT_SUCCESS;
        }catch (ServiceException e){
            logger.error("create book mark error",e);
        }
        return result;
    }

}
