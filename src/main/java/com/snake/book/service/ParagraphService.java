package com.snake.book.service;

import com.base.exception.DaoException;
import com.base.exception.ServiceException;
import com.base.service.BasicService;
import com.snake.book.dao.IParagraphDao;
import com.snake.book.model.Paragraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wen on 2015/5/1.
 */
@Service
@Repository("paragraphService")
public class ParagraphService extends BasicService<Paragraph> implements IParagraphService {

    @Autowired
    @Qualifier("paragraphDao")
    private IParagraphDao paragraphDao;

    @Override
    public IParagraphDao getDao() {
        return paragraphDao;
    }

    public Integer getReadPageNo(Long userId, Long bookId) throws ServiceException {
        try {
            return this.paragraphDao.getReadPageNo(userId,bookId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void updateReadRecord(Long userId, Long bookId, Integer pageNo, String time) throws ServiceException {
        try {
            this.paragraphDao.updateReadRecord(userId, bookId,pageNo,time);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void createReadRecord(Long userId, Long bookId, String time) throws ServiceException{
        try {
            this.paragraphDao.createReadRecord(userId, bookId,time);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void deleteByBookId(Long id) throws ServiceException {
        try {
            this.paragraphDao.deleteByBookId(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Paragraph> getListByBookId(Long bookId, int start, int count) throws ServiceException {
        try {
            return this.paragraphDao.getListByBookId(bookId, start, count);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
