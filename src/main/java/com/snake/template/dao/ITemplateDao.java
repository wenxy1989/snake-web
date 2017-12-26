package com.snake.template.dao;

import com.base.dao.IBasicDao;
import com.base.exception.DaoException;
import com.snake.template.model.Template;

import java.util.List;

/**
 * Created by wenxy on 2016/11/29.
 */
public interface ITemplateDao extends IBasicDao<Template> {
    public List<Template> selectListByCode(String code) throws DaoException;
}
