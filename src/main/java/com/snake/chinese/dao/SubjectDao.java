package com.snake.chinese.dao;

import com.base.dao.MybatisBasicDao;
import com.snake.chinese.model.relation.Subject;
import org.springframework.stereotype.Repository;

/**
 * Created by wen on 2015/5/1.
 */
@Repository("subjectDao")
public class SubjectDao extends MybatisBasicDao<Subject> implements ISubjectDao {

    public SubjectDao() {
        super(Subject.class);
    }
}
