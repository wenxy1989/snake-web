package com.snake.book.controller;

import com.base.Constants;
import com.base.exception.ServiceException;
import com.base.util.Condition;
import com.base.util.Criteria;
import com.base.util.DateTimeUtils;
import com.base.util.SimpleCriteria;
import com.snake.book.model.Book;
import com.snake.book.service.IBookService;
import com.snake.chinese.service.IWordService;
import com.snake.system.controller.BasicController;
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
@RequestMapping("/book/book/")
public class BookController extends BasicController {

    @Resource(name = "bookService")
    private IBookService bookService;

    @Resource(name = "wordService")
    private IWordService wordService;

    @RequestMapping(value = "page")
    public ModelAndView page(Integer pageSize,Integer pageNo,HttpServletRequest request){
        ModelAndView mv = new ModelAndView("/book/book/list");
        String name = request.getParameter("name");
        String book = request.getParameter("book");
        Criteria c = new SimpleCriteria();
        if(StringUtils.isNotEmpty(name)){
            c.addCondition(0,new Condition("name_","like","%"+name+"%"));
            mv.addObject("name",name);
        }
        if(StringUtils.isNotEmpty(book)){
            c.addCondition(0,new Condition("book_","=",book));
            mv.addObject("book", book);
        }
        try {
            c.setPageNo(pageNo == null?1:pageNo);
            c.setFetchSize(pageSize == null?Constants.PAGE_SIZE:pageSize);
            Page<Book> page = bookService.getList(c);
            mv.addObject("page",page);
        }catch (ServiceException e){
            logger.error("获取电子书信息失败",e);
        }
        return mv;
    }

    @RequestMapping(value = "toAdd")
    public ModelAndView toAdd(){
        ModelAndView mv = new ModelAndView("/book/book/add");
        return mv;
    }

    @RequestMapping(value = "add")
    public RedirectView add(Book book){
        RedirectView rv = new RedirectView("page");
        String result = RESULT_ERROR;
        try{
            if(null != book) {
                book.setHadPhrase(true);
                book.setCreatedTime(DateTimeUtils.getNowDateTime());
                //todo 将数据库中没有的字加入数据库汉字基本表中
                //todo 并且刷新这本书使用每个汉字的次数，并存入数据库中
                bookService.create(book);
                result = RESULT_SUCCESS;
            }
        }catch (ServiceException e){
            logger.error("add book word book failed",e);
        }
        rv.addStaticAttribute(OPE_RESULT,result);
        return rv;
    }

    @RequestMapping(value = "toEdit")
    public ModelAndView toEdit(Long id){
        ModelAndView mv = new ModelAndView("/book/book/edit");
        try {
            Book book = bookService.getObject(id);
            mv.addObject("obj", book);
        }catch (ServiceException e){
            logger.error("get book failed",e);
        }
        return mv;
    }

    @RequestMapping(value = "edit")
    public RedirectView edit(Book book){
        RedirectView rv = new RedirectView("page");
        try{
            bookService.update(book);
        }catch (ServiceException e){
            logger.error("edit book word book failed",e);
        }
        return rv;
    }

    @RequestMapping(value = "delete")
    public RedirectView delete(Long id){
        RedirectView rv = new RedirectView("page");
        try{
            bookService.delete(id);
        }catch (ServiceException e){
            logger.error("add book word book failed",e);
        }
        return rv;
    }

    @RequestMapping(value = "details")
    public ModelAndView details(Integer pageSize,Integer start,HttpServletRequest request){
        ModelAndView mv = new ModelAndView("/book/book/details");
        String name = request.getParameter("name");
        Criteria c = new SimpleCriteria();
        if(StringUtils.isNotEmpty(name)){
            c.addCondition(0,new Condition("name_","like","%"+name+"%"));
        }
        try {
            c.setFetchSize(pageSize == null ? 5 : pageSize);
            c.setStart(start == null ? 0 : start);
            Page<Book> page = bookService.getList(c);
            mv.addObject("page", page);
        }catch (ServiceException e){
            logger.error("获取电子书信息失败",e);
        }
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "parseWord")
    public Object parseWord(Long id){
        String result = RESULT_ERROR;
        try{
            Book book = bookService.getObject(id);
            wordService.saveChineseWordSet(id,book.getFile());
            result = RESULT_SUCCESS;
        }catch (ServiceException e){
            logger.error("get chinese word or save word error", e);
        }
        return result;
    }

}
