package com.snake.template.dao;

import com.base.dao.MybatisBasicDao;
import com.snake.template.model.Action;

/**
 * Created by wenxy on 2016/11/29.
 */
public class ActionDao extends MybatisBasicDao<Action> implements IActionDao {

    public ActionDao() {
        super(Action.class);
    }

}
