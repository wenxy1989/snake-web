package com.snake.template.dao;

import com.base.dao.IBasicDao;
import com.snake.template.model.TemplateConfig;

import java.util.List;

/**
 * Created by wenxy on 2016/11/29.
 */
public interface ITemplateDao extends IBasicDao<TemplateConfig> {

    List<TemplateConfig> selectListByFrameId(Long frameId) throws Exception;
}
