package com.snake.chinese.controller;

import com.base.Constants;
import com.base.exception.ServiceException;
import com.base.util.Condition;
import com.base.util.Criteria;
import com.base.util.DateTimeUtils;
import com.base.util.SimpleCriteria;
import com.snake.book.ChineseTextUtils;
import com.snake.chinese.model.Phrase;
import com.snake.chinese.model.Word;
import com.snake.chinese.model.relation.PhraseWord;
import com.snake.chinese.service.IPhraseService;
import com.snake.chinese.utils.PhraseUtils;
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
 * 短语
 * Created by wen on 2015/5/1.
 */
@Controller
@RequestMapping("/book/phrase/")
public class PhraseController extends BasicController {

    @Resource(name = "phraseService")
    private IPhraseService phraseService;

    @RequestMapping(value = "page")
    public ModelAndView page(Integer pageSize,Integer pageNo,HttpServletRequest request){
        ModelAndView mv = new ModelAndView("/chinese/phrase/list");
        String value = request.getParameter("value");
        String paragraphIdStr = request.getParameter("paragraphId");
        Criteria c = new SimpleCriteria();
        if(StringUtils.isNotEmpty(paragraphIdStr)) {
            Long paragraphId = Long.valueOf(paragraphIdStr);
            c.addCondition(0,new Condition("book_id","=",paragraphId));
            mv.addObject("paragraphId",paragraphId);
        }
        if(StringUtils.isNotEmpty(value)){
            c.addCondition(0,new Condition("phrase_"," like","%"+value+"%"));
            mv.addObject("value",value);
        }
        try {
            c.setPageNo(pageNo == null?1:pageNo);
            c.setFetchSize(pageSize == null?Constants.PAGE_SIZE:pageSize);
            Page<Phrase> page = phraseService.getList(c);
            mv.addObject("page",page);
        }catch (ServiceException e){
            logger.error("获取段落信息失败",e);
        }
        return mv;
    }

    @RequestMapping(value = "toAdd")
    public ModelAndView toAdd(){
        ModelAndView mv = new ModelAndView("/chinese/phrase/add");
        return mv;
    }

    @RequestMapping(value = "add")
    public RedirectView add(Phrase phrase){
        RedirectView rv = new RedirectView("page");
        try{
            phrase.setCreatedTime(DateTimeUtils.getNowDateTime());
            phraseService.create(phrase);
        }catch (ServiceException e){
            logger.error("add book phrase failed",e);
        }
        return rv;
    }

    @RequestMapping(value = "analysis")
    public ModelAndView analysis(Long id){
        ModelAndView mv = new ModelAndView("/chinese/phrase/edit");
        try {
            Phrase phrase = phraseService.getObject(id);
            if(null != phrase){
                List<PhraseWord> phraseWordList = phraseService.getPhraseWordListByPhraseId(id);
                if(null != phraseWordList && phraseWordList.size() > 1){
                    mv.addObject("phraseWordList",phraseWordList);
                }else{
                    List<Word> wordList = PhraseUtils.getWordList(phrase.getPhrase());
                    mv.addObject("wordList",wordList);
                }
                mv.addObject("obj", phrase);
            }
        }catch (ServiceException e){
            logger.error("get phrase failed",e);
        }
        return mv;
    }

    @RequestMapping(value = "toEdit")
    public ModelAndView toEdit(Long id){
        ModelAndView mv = new ModelAndView("/chinese/phrase/edit");
        try {
            Phrase phrase = phraseService.getObject(id);
            if(null != phrase){
                List<PhraseWord> phraseWordList = phraseService.getPhraseWordListByPhraseId(id);
                if(null != phraseWordList && phraseWordList.size() > 1){
                    mv.addObject("phraseWordList",phraseWordList);
                }else{
                    List<Word> words = new ArrayList<Word>();
                    List<String> list = new ArrayList<String>();
                    ChineseTextUtils.getSingleChineseWordList(phrase.getPhrase(),list);
                    for(String each : list){
                        words.add(new Word(each));
                    }
                    mv.addObject("wordList",words);
                }
                mv.addObject("obj", phrase);
            }
        }catch (ServiceException e){
            logger.error("get phrase failed",e);
        }
        return mv;
    }

    @RequestMapping(value = "edit")
    public RedirectView edit(Phrase phrase){
        RedirectView rv = new RedirectView("page");
        try{
            phraseService.update(phrase);
        }catch (ServiceException e){
            logger.error("edit book phrase failed",e);
        }
        return rv;
    }

    @RequestMapping(value = "delete")
    public RedirectView delete(Long id){
        RedirectView rv = new RedirectView("page");
        try{
            phraseService.delete(id);
        }catch (ServiceException e){
            logger.error("add book phrase failed",e);
        }
        return rv;
    }

    @ResponseBody
    @RequestMapping(value = "savePhraseWord")
    public Object savePhraseWord(Long id){
        String result = RESULT_ERROR;
        try {
            Phrase phrase = phraseService.getObject(id);
            if(null != phrase){
                List<PhraseWord> phraseWords = new ArrayList<PhraseWord>();
                List<Word> list  = PhraseUtils.getWordList(phrase.getPhrase());
                for(Word word:list){
                    PhraseWord phraseWord = new PhraseWord();
                    phraseWord.setWordId(word.getId());
                    phraseWord.setPhraseId(id);
                    phraseWords.add(phraseWord);
                }
                phraseService.batchInsertPhraseWord(phraseWords);
                result = RESULT_SUCCESS;
            }
        }catch (ServiceException e){
            logger.error("batch insert phrase_word failed",e);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "updateStructureId")
    public Object updateStructureId(Long id,Long structureId){
        String result = RESULT_ERROR;
        try{
            Phrase phrase = new Phrase();
            phrase.setId(id);
            phrase.setStructureId(structureId);
            phraseService.update(phrase);
            result = RESULT_SUCCESS;
        }catch (ServiceException e){
            logger.error("更新语句结构失败",e);
        }
        return result;
    }

}
