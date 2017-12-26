package com.snake.chinese.service;

import com.base.service.BasicService;
import com.snake.chinese.dao.ISubjectDao;
import com.snake.chinese.model.relation.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * Created by wen on 2015/5/1.
 */
@Service
@Repository("subjectService")
public class SubjectService extends BasicService<Subject> implements ISubjectService {

    @Autowired
    @Qualifier("subjectDao")
    private ISubjectDao subjectDao;

    @Override
    public ISubjectDao getDao() {
        return subjectDao;
    }
}
