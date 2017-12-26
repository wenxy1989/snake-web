package com.snake.chinese.service;

import com.base.exception.DaoException;
import com.base.exception.ServiceException;
import com.base.service.BasicService;
import com.snake.chinese.dao.IWordDao;
import com.snake.chinese.dao.IWordTypeDao;
import com.snake.chinese.model.Word;
import com.snake.chinese.model.relation.WordType;
import com.snake.chinese.utils.MainLogicInstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wen on 2015/5/1.
 */
@Service
@Repository("wordService")
public class WordService extends BasicService<Word> implements IWordService {

    @Autowired
    @Qualifier("wordDao")
    private IWordDao wordDao;

    @Autowired
    @Qualifier("wordTypeDao")
    private IWordTypeDao wordTypeDao;

    @Override
    public IWordDao getDao() {
        return wordDao;
    }


    public List<Word> getListByValue(String word) throws ServiceException {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("word_", word);
            return this.wordDao.find(map);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void deleteByBookId(Long id) throws ServiceException {
        try {
            this.wordDao.deleteByBookId(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Word getObjectByValue(String value) throws ServiceException {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("word_", value);
            return wordDao.findOne(map);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<WordType> getWordTypeListByWordId(Long wordId) throws ServiceException {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("word_id", wordId);
            return wordTypeDao.find(map);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void removeType(Long wordId, Long typeId) throws ServiceException {
        try {
            WordType wordType = new WordType();
            wordType.setWordId(wordId);
            wordType.setTypeId(typeId);
            wordTypeDao.deleteObject(wordType);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void addType(Long wordId, Long typeId) throws ServiceException {
        try {
            WordType wordType = new WordType();
            wordType.setWordId(wordId);
            wordType.setTypeId(typeId);
            wordTypeDao.create(wordType);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void saveChineseWordSet(Long bookId, String file) throws ServiceException {
        try {
            Map<String, Integer> map = MainLogicInstants.getInstant().getChineseCountMap(new File(file));
            List<Word> list = new ArrayList<Word>();
            for (String each : map.keySet()) {
                Word word = new Word(each);
                word.setBookId(bookId);
                word.setCount(map.get(each));
                list.add(word);
                if(list.size() == 100){
                    getDao().batchInsert(list);
                    list.clear();
                }
            }
            getDao().batchInsert(list);
        } catch (IOException e) {
            throw new ServiceException(e);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
