package com.snake.template.dao;

import com.base.dao.MybatisBasicDao;
import com.snake.template.model.Attribute;

/**
 * Created by wenxy on 2016/11/29.
 */
public class AttributeDao extends MybatisBasicDao<Attribute> implements IAttributeDao {

    public AttributeDao() {
        super(Attribute.class);
    }

}
