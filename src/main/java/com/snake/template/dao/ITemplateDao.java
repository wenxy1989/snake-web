package com.snake.template.dao;

import com.base.dao.IBasicDao;
import com.base.exception.DaoException;
import com.snake.template.model.TemplateConfig;

import java.util.List;

/**
 * Created by wenxy on 2016/11/29.
 */
public interface ITemplateDao extends IBasicDao<TemplateConfig> {
    public List<TemplateConfig> selectListByCode(String code) throws DaoException;
}
