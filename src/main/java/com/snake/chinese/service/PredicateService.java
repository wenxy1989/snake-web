package com.snake.chinese.service;

import com.base.service.BasicService;
import com.snake.chinese.dao.IPredicateDao;
import com.snake.chinese.model.relation.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * Created by wen on 2015/5/1.
 */
@Service
@Repository("predicateService")
public class PredicateService extends BasicService<Predicate> implements IPredicateService {

    @Autowired
    @Qualifier("predicateDao")
    private IPredicateDao predicateDao;

    @Override
    public IPredicateDao getDao() {
        return predicateDao;
    }
}
