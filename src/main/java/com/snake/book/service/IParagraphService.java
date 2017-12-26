package com.snake.book.service;

import com.base.exception.ServiceException;
import com.base.service.IBasicService;
import com.snake.book.model.Paragraph;

import java.util.List;

/**
 * Created by wen on 2015/5/1.
 */
public interface IParagraphService extends IBasicService<Paragraph> {

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
     * @throws ServiceException
     */
    Integer getReadPageNo(Long userId,Long bookId) throws ServiceException;

    void createReadRecord(Long userId, Long bookId, String nowDateTime) throws ServiceException;

    void deleteByBookId(Long id) throws ServiceException;

    public List<Paragraph> getListByBookId(Long bookId, int start, int count) throws ServiceException;
}
