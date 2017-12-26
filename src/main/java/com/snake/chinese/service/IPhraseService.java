package com.snake.chinese.service;

import com.base.exception.ServiceException;
import com.base.service.IBasicService;
import com.snake.book.model.Paragraph;
import com.snake.chinese.model.Phrase;
import com.snake.chinese.model.relation.PhraseWord;

import java.util.List;

/**
 * Created by wen on 2015/5/1.
 */
public interface IPhraseService extends IBasicService<Phrase> {
    void batchInsert(List<Phrase> paragraphs) throws ServiceException;

    /**
     * 更新或者创建阅读记录
     * @param userId
     * @param bookId
     * @param pageNo
     *@param time  @throws ServiceException
     */
    void updateReadRecord(Long userId, Long bookId, Integer pageNo, String time) throws ServiceException;

    /**
     * 获取用户阅读电子书的页数
     * @param userId
     * @param bookId
     * @return
     * @throws com.base.exception.ServiceException
     */
    Integer getReadPageNo(Long userId, Long bookId) throws ServiceException;

    void createReadRecord(Long userId, Long bookId, String nowDateTime) throws ServiceException;

    void deleteByBookId(Long id) throws ServiceException;

    List<PhraseWord> getPhraseWordListByPhraseId(Long id) throws ServiceException;

    void batchInsertPhraseWord(List<PhraseWord> list) throws ServiceException;

    List<Phrase> getListByBookId(Long bookId, int start, int count) throws ServiceException;
}
