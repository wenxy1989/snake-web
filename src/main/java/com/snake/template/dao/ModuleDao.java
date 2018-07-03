package com.snake.template.dao;

import com.base.dao.MybatisBasicDao;
import com.snake.template.model.Module;

/**
 * Created by wenxy on 2016/11/29.
 */
public class ModuleDao extends MybatisBasicDao<Module> implements IModuleDao {

    public ModuleDao() {
        super(Module.class);
    }

}
