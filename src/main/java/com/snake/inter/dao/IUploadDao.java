package com.snake.inter.dao;

import com.base.dao.IBasicDao;
import com.base.exception.DaoException;
import com.snake.inter.model.Upload;

import java.util.List;

public interface IUploadDao extends IBasicDao<Upload> {
    public List getListByUrlId(Long id) throws DaoException;
}