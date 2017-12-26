package com.snake.book.controller;

import com.base.exception.ServiceException;
import com.snake.book.model.Book;
import com.snake.book.model.Paragraph;
import com.snake.book.service.IBookService;
import com.snake.book.service.IParagraphService;
import com.snake.chinese.model.Phrase;
import com.snake.chinese.model.Word;
import com.snake.chinese.service.IPhraseService;
import com.snake.chinese.service.IWordService;
import com.snake.system.controller.BasicController;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wenxy on 2017/3/11.
 */
@Controller
@RequestMapping(value = "/book/api/")
public class BookAPIController extends BasicController {

    @Resource(name = "bookService")
    private IBookService bookService;

    @Resource(name = "paragraphService")
    private IParagraphService paragraphService;

    @Resource(name = "phraseService")
    private IPhraseService phraseService;

    @Resource(name = "wordService")
    private IWordService wordService;

    @ResponseBody
    @RequestMapping(value = "book/stat")
    public Object bookStat() {
        JSONObject json = new JSONObject();
        try {
            Integer count = bookService.getCount();
            json.put("count", count);
        } catch (ServiceException e) {
            logger.error("book stat error", e);
        }
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "book/list")
    public Object bookList(@RequestBody JSONObject json) {
        List<Book> list = null;
        Integer start = 0;
        Integer count = 20;
        try {
            if (null != json) {
                start = (Integer) json.get("start");
                count = (Integer) json.get("count");
            }
            list = bookService.getList(start == null ? 0 : start, count == null ? 20 : count);
        } catch (ServiceException e) {
            logger.error("book list error", e);
        }
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "paragraph/stat")
    public Object paragraphStat() {
        JSONObject json = new JSONObject();
        try {
            Integer count = paragraphService.getCount();
            json.put("count", count);
        } catch (ServiceException e) {
            logger.error("paragraph stat error", e);
        }
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "paragraph/list")
    public Object paragraphList(@RequestBody JSONObject json) {
        logger.info(json.toString());
        List<Paragraph> list = null;
        Integer bookId = 0;
        Integer start = 0;
        Integer count = 20;
        try {
            if (null != json) {
                bookId = (Integer) json.get("bookId");
                start = (Integer) json.get("start");
                count = (Integer) json.get("count");
            }
            list = paragraphService.getListByBookId(Long.valueOf(bookId), start == null ? 0 : start, count == null ? 20 : count);
        } catch (ServiceException e) {
            logger.error("paragraph list error", e);
        }
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "phrase/stat")
    public Object phraseStat() {
        JSONObject json = new JSONObject();
        try {
            Integer count = phraseService.getCount();
            json.put("count", count);
        } catch (ServiceException e) {
            logger.error("paragraph stat error", e);
        }
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "phrase/list")
    public Object phraseList(@RequestBody JSONObject json) {
        logger.info(json.toString());
        List<Phrase> list = null;
        Integer bookId = 0;
        Integer start = 0;
        Integer count = 20;
        try {
            if (null != json) {
                bookId = (Integer) json.get("bookId");
                start = (Integer) json.get("start");
                count = (Integer) json.get("count");
            }
            list = phraseService.getListByBookId(Long.valueOf(bookId), start == null ? 0 : start, count == null ? 20 : count);
        } catch (ServiceException e) {
            logger.error("phrase list error", e);
        }
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "word/stat")
    public Object wordStat() {
        JSONObject json = new JSONObject();
        try {
            Integer count = wordService.getCount();
            json.put("count", count);
        } catch (ServiceException e) {
            logger.error("word stat error", e);
        }
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "word/list")
    public Object wordList(@RequestBody JSONObject json) {
        List<Word> list = null;
        Integer start = 0;
        Integer count = 20;
        try {
            if (null != json) {
                start = (Integer) json.get("start");
                count = (Integer) json.get("count");
            }
            list = wordService.getList(start == null ? 0 : start, count == null ? 20 : count);
        } catch (ServiceException e) {
            logger.error("book list error", e);
        }
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "word/verified")
    public Object wordVerified(@RequestBody JSONArray array) {
        String result = RESULT_ERROR;
        try {
            if (null != array && array.size() > 0) {
                for (int i = 0; i < array.size(); i++) {
                    long id = (Integer) array.get(i);
                    Word word = new Word();
                    word.setId(id);
                    word.setVerified(true);
                    wordService.update(word);
                }
            }
            result = RESULT_SUCCESS;
        } catch (ServiceException e) {
            logger.error("word verified error", e);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "word/veto")
    public Object wordVeto(@RequestBody JSONArray array) {
        String result = RESULT_ERROR;
        try {
            if (null != array && array.size() > 0) {
                for (int i = 0; i < array.size(); i++) {
                    long id = (Integer) array.get(i);
                    Word word = new Word();
                    word.setId(id);
                    word.setVerified(false);
                    wordService.update(word);
                }
            }
            result = RESULT_SUCCESS;
        } catch (ServiceException e) {
            logger.error("word veto error", e);
        }
        return result;
    }

}
