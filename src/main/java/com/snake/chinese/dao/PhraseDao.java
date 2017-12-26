package com.snake.chinese.dao;

import com.base.dao.MybatisBasicDao;
import com.base.exception.DaoException;
import com.snake.book.model.Paragraph;
import com.snake.chinese.model.Phrase;
import com.snake.chinese.model.relation.PhraseWord;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wen on 2015/5/1.
 */
@Repository("phraseDao")
public class PhraseDao extends MybatisBasicDao<Phrase> implements IPhraseDao {

    public PhraseDao() {
        super(Phrase.class);
    }

    public void batchInsert(List<Phrase> list) throws DaoException {
        this.sqlSession.insert(Phrase.class.getName()+".batchInsert",list);
    }

    public void createReadRecord(Long userId, Long bookId, String time) throws DaoException {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("userId",userId);
        map.put("bookId",bookId);
        map.put("createdTime",time);
        this.sqlSession.insert(Phrase.class.getName() + ".createReadRecord", map);
    }

    public void updateReadRecord(Long userId, Long bookId, Integer pageNo, String time) throws DaoException {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("userId",userId);
        map.put("bookId",bookId);
        map.put("pageNo",pageNo);
        map.put("editTime",time);
        this.sqlSession.update(Phrase.class.getName() + ".updateReadRecord", map);

    }

    public Integer getReadPageNo(Long userId, Long bookId) throws DaoException {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("userId",userId);
        map.put("bookId",bookId);
        return (Integer)this.sqlSession.selectOne(Phrase.class.getName()+".getReadPageNo",map);
    }

    public void deleteByBookId(Long id) throws DaoException {
        this.sqlSession.delete(Phrase.class.getName() + ".deleteByBookId", id);
    }

    public List<PhraseWord> getPhraseWordListByPhraseId(Long phraseId) throws DaoException {
        return this.sqlSession.selectList(Phrase.class.getName() + ".getPhraseWordListByPhraseId", phraseId);
    }

    public void batchInsertPhraseWord(List<PhraseWord> list) throws DaoException {
        this.sqlSession.delete(Phrase.class.getName() + ".batchInsertPhraseWord", list);
    }

    @Override
    public List<Phrase> getListByBookId(Long bookId, int start, int count) throws DaoException {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("bookId",bookId);
        map.put("offset",start);
        map.put("limit", count);
        return this.sqlSession.selectList(Phrase.class.getName()+".selectSomeByBookId",map);
    }
}
