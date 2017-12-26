package com.snake.chinese.dao;

import com.base.dao.IBasicDao;
import com.base.exception.DaoException;
import com.snake.book.model.Paragraph;
import com.snake.chinese.model.Phrase;
import com.snake.chinese.model.relation.PhraseWord;

import java.util.List;

/**
 * Created by wen on 2015/5/1.
 */
public interface IPhraseDao extends IBasicDao<Phrase> {
    void batchInsert(List<Phrase> paragraphs) throws DaoException;

    Integer getReadPageNo(Long userId, Long bookId) throws DaoException;

    void updateReadRecord(Long userId, Long bookId, Integer pageNo, String time) throws DaoException;

    void createReadRecord(Long userId, Long bookId, String time) throws DaoException;

    void deleteByBookId(Long id) throws DaoException;

    void batchInsertPhraseWord(List<PhraseWord> list) throws DaoException;

    List<PhraseWord> getPhraseWordListByPhraseId(Long phraseId) throws DaoException;

    List<Phrase> getListByBookId(Long bookId, int start, int count) throws DaoException;
}
