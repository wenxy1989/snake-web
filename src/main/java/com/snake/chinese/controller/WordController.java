package com.snake.chinese.controller;

import com.base.Constants;
import com.base.exception.ServiceException;
import com.base.util.Condition;
import com.base.util.Criteria;
import com.base.util.DateTimeUtils;
import com.base.util.SimpleCriteria;
import com.snake.book.model.Book;
import com.snake.book.service.IBookService;
import com.snake.chinese.model.Word;
import com.snake.chinese.model.relation.WordType;
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
import java.util.List;

/**
 * Created by wen on 2015/5/1.
 */
@Controller
@RequestMapping("/book/word/")
public class WordController extends BasicController {

    @Resource(name = "wordService")
    private IWordService wordService;

    @Resource(name = "bookService")
    private IBookService bookService;

    @RequestMapping(value = "page")
    public ModelAndView page(Integer pageSize, Integer pageNo, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/chinese/word/list");
        String value = request.getParameter("value");
        String typeId = request.getParameter("typeId");
        String order = request.getParameter("order");
        Criteria c = new SimpleCriteria();
        if (StringUtils.isNotEmpty(value)) {
            c.addCondition(0, new Condition("word_", "like", value + "%"));
            mv.addObject("value", value);
        }
        if (StringUtils.isNotEmpty(typeId)) {
            if ("null".equals(typeId)) {
                c.addCondition(0, new Condition("type_id", "is", null));
            } else {
                c.addCondition(0, new Condition("type_id", "=", typeId));
            }
            mv.addObject("typeId", typeId);
        }
        if (StringUtils.isNotBlank(order)) {
            c.addOrder(0, order + " asc");
            mv.addObject("order", order);
        } else {
            c.addOrder(0, "id_ asc");
        }
        try {
            c.setPageNo(pageNo == null ? 1 : pageNo);
            c.setFetchSize(pageSize == null ? Constants.PAGE_SIZE : pageSize);
            Page<Word> page = wordService.getList(c);
            mv.addObject("page", page);
        } catch (ServiceException e) {
            logger.error("获取词组信息失败", e);
        }
        return mv;
    }

    @RequestMapping(value = "toAdd")
    public ModelAndView toAdd() {
        ModelAndView mv = new ModelAndView("/chinese/word/add");
        return mv;
    }

    @RequestMapping(value = "add")
    public RedirectView add(Word word) {
        RedirectView rv = new RedirectView("page");
        try {
            word.setCreatedTime(DateTimeUtils.getNowDateTime());
            wordService.create(word);
        } catch (ServiceException e) {
            logger.error("add book word word failed", e);
        }
        return rv;
    }

    @RequestMapping(value = "toEdit")
    public ModelAndView toEdit(Long id) {
        ModelAndView mv = new ModelAndView("/chinese/word/edit");
        try {
            Word word = wordService.getObject(id);
            mv.addObject("obj", word);
        } catch (ServiceException e) {
            logger.error("get word failed", e);
        }
        return mv;
    }

    @RequestMapping(value = "edit")
    public RedirectView edit(Word word) {
        RedirectView rv = new RedirectView("page");
        try {
            wordService.update(word);
        } catch (ServiceException e) {
            logger.error("edit book word word failed", e);
        }
        return rv;
    }

    @RequestMapping(value = "delete")
    public RedirectView delete(Long id) {
        RedirectView rv = new RedirectView("page");
        try {
            wordService.delete(id);
        } catch (ServiceException e) {
            logger.error("add book word word failed", e);
        }
        return rv;
    }

    @RequestMapping(value = "details")
    public ModelAndView details(Integer pageSize, Integer start, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/chinese/word/details");
        String name = request.getParameter("name");
        Criteria c = new SimpleCriteria();
        if (StringUtils.isNotEmpty(name)) {
            c.addCondition(0, new Condition("name_", "like", "%" + name + "%"));
        }
        try {
            c.setFetchSize(pageSize == null ? 5 : pageSize);
            c.setStart(start == null ? 0 : start);
            Page<Word> page = wordService.getList(c);
            mv.addObject("page", page);
        } catch (ServiceException e) {
            logger.error("获取词组信息失败", e);
        }
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "addWord")
    public Object addWord(Word word) {
        String result = RESULT_ERROR;
        try {
            List<Word> list = wordService.getListByValue(word.getWord());
            if (null != list) {
                for (Word obj : list) {
                    if (!obj.getVerified()) {
                        obj.setVerified(true);
                        wordService.update(obj);
                    }
                    wordService.addType(obj.getId(), word.getTypeId());
                }
            } else {
                word.setVerified(true);
                word.setCount(0);
                word.setCreatedTime(DateTimeUtils.getNowDateTime());
                wordService.create(word);
            }
            result = RESULT_SUCCESS;
        } catch (ServiceException e) {
            logger.error("create word error", e);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "loadWordTypeList")
    public Object loadWordTypeList(Long id) {
        List<WordType> list = null;
        try {
            list = wordService.getWordTypeListByWordId(id);
        } catch (ServiceException e) {
            logger.error("get typeId list by wordId error", e);
        }
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "deleteByBookId")
    public Object deleteByBookId(Long bookId) {
        String result = RESULT_ERROR;
        try {
            wordService.deleteByBookId(bookId);
            Book book = new Book();
            book.setId(bookId);
//            book.setHadPhrase(false);
            book.setHadWord(false);
            bookService.update(book);
            result = RESULT_SUCCESS;
        } catch (ServiceException e) {
            logger.error("delete book paragraph failed", e);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "addType")
    public Object addType(Long id, Long typeId) {
        String result = RESULT_ERROR;
        try {
            wordService.addType(id, typeId);
            result = RESULT_SUCCESS;
        } catch (ServiceException e) {
            logger.error("add typeId by wordId error", e);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "removeType")
    public Object removeType(Long id, Long typeId) {
        String result = null;
        try {
            wordService.removeType(id, typeId);
            result = RESULT_SUCCESS;
        } catch (ServiceException e) {
            logger.error("remove typeId by wordId error", e);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "verify")
    public Object verify(Long id) {
        String result = RESULT_ERROR;
        try {
            Word word = wordService.getObject(id);
            word.setVerified(true);
            wordService.update(word);
            result = RESULT_SUCCESS;
        } catch (ServiceException e) {
            logger.error("remove typeId by wordId error", e);
        }
        return result;
    }

}
