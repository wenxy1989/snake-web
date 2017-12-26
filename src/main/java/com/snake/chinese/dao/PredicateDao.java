package com.snake.chinese.dao;

import com.base.dao.MybatisBasicDao;
import com.snake.chinese.model.relation.Predicate;
import org.springframework.stereotype.Repository;

/**
 * Created by wen on 2015/5/1.
 */
@Repository("predicateDao")
public class PredicateDao extends MybatisBasicDao<Predicate> implements IPredicateDao {

    public PredicateDao() {
        super(Predicate.class);
    }
}
