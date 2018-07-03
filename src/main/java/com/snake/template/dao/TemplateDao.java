package com.snake.template.dao;

import com.base.dao.MybatisBasicDao;
import com.base.exception.DaoException;
import com.snake.template.model.Template;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wenxy on 2016/11/29.
 */
public class TemplateDao extends MybatisBasicDao<Template> implements ITemplateDao {

    public TemplateDao() {
        super(Template.class);
    }

    public List<Template> selectListByCode(String code) throws DaoException {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("code_",code);
        return super.find(map);
    }
}
