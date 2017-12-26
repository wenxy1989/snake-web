package com.snake.chinese.service;

import com.base.service.BasicService;
import com.snake.chinese.dao.IElementDao;
import com.snake.chinese.model.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * Created by wen on 2015/5/1.
 */
@Service
@Repository("elementService")
public class ElementService extends BasicService<Element> implements IElementService {

    @Autowired
    @Qualifier("elementDao")
    private IElementDao elementDao;

    @Override
    public IElementDao getDao() {
        return elementDao;
    }
}
