package com.snake.book.controller;

import com.base.Constants;
import com.base.exception.ServiceException;
import com.base.util.Condition;
import com.base.util.Criteria;
import com.base.util.DateTimeUtils;
import com.base.util.SimpleCriteria;
import com.snake.book.model.Paragraph;
import com.snake.book.service.IParagraphService;
import com.snake.chinese.model.Phrase;
import com.snake.chinese.service.IPhraseService;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wen on 2015/5/1.
 */
@Controller
@RequestMapping("/book/paragraph/")
public class ParagraphController extends BasicController {

    @Resource(name = "paragraphService")
    private IParagraphService paragraphService;

    @Resource(name = "phraseService")
    private IPhraseService phraseService;

    @RequestMapping(value = "page")
    public ModelAndView page(Integer pageSize, Integer pageNo, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/book/paragraph/list");
        String value = request.getParameter("value");
        String bookIdStr = request.getParameter("bookId");
        if (StringUtils.isNotEmpty(bookIdStr)) {
            Long bookId = Long.valueOf(bookIdStr);
            Criteria c = new SimpleCriteria();
            c.addCondition(0, new Condition("book_id", "=", bookId));
            mv.addObject("bookId", bookId);
            if (StringUtils.isNotEmpty(value)) {
                c.addCondition(0, new Condition("(name_ like '%" + value + "%' ", "or content_ like '%" + value + "% ')", ""));
                mv.addObject("value", value);
            }
            Long userId = getLoginUserId(request);
            try {
                if (StringUtils.isBlank(value)) {
                    if (null == pageNo) {
                        pageNo = paragraphService.getReadPageNo(userId, bookId);
                        if (null == pageNo) {
                            paragraphService.createReadRecord(userId, bookId, DateTimeUtils.getNowDateTime());
                            pageNo = 1;
                        }
                    } else {
                        paragraphService.updateReadRecord(userId, bookId, pageNo, DateTimeUtils.getNowDateTime());
                    }
                }
                c.setPageNo(pageNo == null ? Constants.PAGE_NO : pageNo);
                c.setFetchSize(pageSize == null ? 20 : pageSize);
                Page<Paragraph> page = paragraphService.getList(c);
                mv.addObject("page", page);
            } catch (ServiceException e) {
                logger.error("获取段落信息失败", e);
            }
        }
        return mv;
    }

    @RequestMapping(value = "toAdd")
    public ModelAndView toAdd() {
        ModelAndView mv = new ModelAndView("/book/paragraph/add");
        return mv;
    }

    @RequestMapping(value = "add")
    public RedirectView add(Paragraph paragraph) {
        RedirectView rv = new RedirectView("page");
        try {
            paragraph.setCreatedTime(DateTimeUtils.getNowDateTime());
            paragraphService.create(paragraph);
        } catch (ServiceException e) {
            logger.error("add book paragraph failed", e);
        }
        return rv;
    }

    @RequestMapping(value = "toEdit")
    public ModelAndView toEdit(Long id) {
        ModelAndView mv = new ModelAndView("/book/paragraph/edit");
        try {
            Paragraph paragraph = paragraphService.getObject(id);
            mv.addObject("obj", paragraph);
        } catch (ServiceException e) {
            logger.error("get paragraph failed", e);
        }
        return mv;
    }

    @RequestMapping(value = "edit")
    public RedirectView edit(Paragraph paragraph) {
        RedirectView rv = new RedirectView("page");
        try {
            paragraphService.update(paragraph);
        } catch (ServiceException e) {
            logger.error("edit book paragraph failed", e);
        }
        return rv;
    }

    @RequestMapping(value = "delete")
    public RedirectView delete(Long id, Long bookId) {
        RedirectView rv = new RedirectView("page");
        rv.addStaticAttribute("bookId", bookId);
        try {
            paragraphService.delete(id);
        } catch (ServiceException e) {
            logger.error("add book paragraph failed", e);
        }
        return rv;
    }

    @ResponseBody
    @RequestMapping(value = "updateValue")
    public Object updateValue(Long id, String value) {
        String result = RESULT_ERROR;
        try {
            Paragraph paragraph = new Paragraph(value);
            paragraph.setId(id);
            paragraphService.update(paragraph);
            result = RESULT_SUCCESS;
        } catch (ServiceException e) {
            logger.error("edit book paragraph failed", e);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "split")
    public Object split(Long id, HttpServletRequest request) {
        String result = RESULT_ERROR;
        try {
            Paragraph paragraph = paragraphService.getObject(id);
            String value = paragraph.getName();
            if (null == value) {
                value = paragraph.getContent();
            }
            if (StringUtils.isNotEmpty(value)) {
                String time = DateTimeUtils.getNowDateTime();
                Long userId = getLoginUserId(request);
                String phraseSymbol = "，";
                if (value.contains(phraseSymbol)) {
                    String[] phraseArray = value.split(phraseSymbol);
                    List<Phrase> phraseList = new ArrayList<Phrase>();
                    for (String str : phraseArray) {
                        if (StringUtils.isNotEmpty(str)) {
                            Phrase phrase = new Phrase(str);
                            phrase.setCreatedTime(time);
                            phrase.setCreatorId(userId);
                            phrase.setParagraphId(id);
                            phraseList.add(phrase);
                        }
                    }
                    phraseService.batchInsert(phraseList);
                } else {
                    Phrase phrase = new Phrase(value);
                    phrase.setCreatedTime(time);
                    phrase.setCreatorId(userId);
                    phrase.setParagraphId(id);
                    phraseService.create(phrase);
                }
            }
            result = RESULT_SUCCESS;
        } catch (ServiceException e) {
            logger.error("split paragraph error,id is " + id, e);
        }
        return result;
    }
}
