package com.snake.chinese.dao;

import com.base.dao.MybatisBasicDao;
import com.base.exception.DaoException;
import com.snake.chinese.model.Word;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wen on 2015/5/1.
 */
@Repository("wordDao")
public class WordDao extends MybatisBasicDao<Word> implements IWordDao {

    public WordDao() {
        super(Word.class);
    }

    public void batchInsert(List<Word> list) throws DaoException {
        this.sqlSession.insert(Word.class.getName() + ".batchInsert", list);
    }

    public void create(Word word) throws DaoException {
        super.create(word);
    }

    public void deleteByBookId(Long id) throws DaoException {
        this.sqlSession.delete(Word.class.getName() + ".deleteByBookId", id);
    }
}
