package com.snake.book.dao;

import com.base.dao.MybatisBasicDao;
import com.base.exception.DaoException;
import com.snake.book.model.Paragraph;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wen on 2015/5/1.
 */
@Repository("paragraphDao")
public class ParagraphDao extends MybatisBasicDao<Paragraph> implements IParagraphDao {

    public ParagraphDao() {
        super(Paragraph.class);
    }

    public void createReadRecord(Long userId, Long bookId, String time) throws DaoException {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("userId",userId);
        map.put("bookId",bookId);
        map.put("createdTime",time);
        this.sqlSession.insert(Paragraph.class.getName()+".createReadRecord",map);
    }

    public void updateReadRecord(Long userId, Long bookId, Integer pageNo, String time) throws DaoException {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("userId",userId);
        map.put("bookId",bookId);
        map.put("pageNo",pageNo);
        map.put("editTime",time);
        this.sqlSession.update(Paragraph.class.getName() + ".updateReadRecord", map);

    }

    public Integer getReadPageNo(Long userId, Long bookId) throws DaoException {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("userId",userId);
        map.put("bookId",bookId);
        return (Integer)this.sqlSession.selectOne(Paragraph.class.getName()+".getReadPageNo",map);
    }

    public void deleteByBookId(Long id) throws DaoException {
        this.sqlSession.delete(Paragraph.class.getName() + ".deleteByBookId", id);
    }

    @Override
    public List<Paragraph> getListByBookId(Long bookId, int start, int count) throws DaoException {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("bookId",bookId);
        map.put("offset",start);
        map.put("limit",count);
        return this.sqlSession.selectList(Paragraph.class.getName()+".selectSomeByBookId",map);
    }
}
