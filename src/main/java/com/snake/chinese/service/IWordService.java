package com.snake.chinese.service;

import com.base.exception.ServiceException;
import com.base.service.IBasicService;
import com.snake.chinese.model.Word;
import com.snake.chinese.model.relation.WordType;

import java.util.List;

/**
 * Created by wen on 2015/5/1.
 */
public interface IWordService extends IBasicService<Word> {

    Word getObjectByValue(String value) throws ServiceException;

    List<WordType> getWordTypeListByWordId(Long wordId) throws ServiceException;

    void addType(Long id, Long typeId) throws ServiceException;

    void removeType(Long id,Long typeId) throws ServiceException;

    void deleteByBookId(Long bookId) throws ServiceException;

    List<Word> getListByValue(String word) throws ServiceException;

    void saveChineseWordSet(Long bookId, String file) throws ServiceException;
}
