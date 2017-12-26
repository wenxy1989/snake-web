package com.snake.book.dao;

import com.base.dao.IBasicDao;
import com.base.exception.DaoException;
import com.snake.book.model.Paragraph;

import java.util.List;

/**
 * Created by wen on 2015/5/1.
 */
public interface IParagraphDao extends IBasicDao<Paragraph> {

    Integer getReadPageNo(Long userId, Long bookId) throws DaoException;

    void updateReadRecord(Long userId, Long bookId, Integer pageNo, String time) throws DaoException;

    void createReadRecord(Long userId, Long bookId, String time) throws DaoException;

    void deleteByBookId(Long id) throws DaoException;

    List<Paragraph> getListByBookId(Long bookId, int start, int count) throws DaoException;
}
