package com.base.dao;
import com.base.util.Criteria;
import com.base.util.Parameter;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * User: qufengfu
 * Date: 13-7-6
 */
public abstract class MybatisBasicDao<T> implements IBasicDao<T> {
    protected transient final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected SqlSession sqlSession;

    protected Class<T> modelClass;
    private String namespace;

    public MybatisBasicDao(Class<T> modelClass) {
        this.modelClass = modelClass;
        this.namespace = modelClass.getName();
    }

    public List<T> getAll() throws Exception {
        return this.sqlSession.selectList(namespace + ".selectAll");
    }

    public List<T> getList(int first, int limit) throws Exception {
        HashMap<String, Integer> map = new HashMap<String, Integer>();

        map.put("offset", first);
        map.put("limit", limit);

        return this.sqlSession.selectList(namespace + ".selectSome", map);
    }

    public Integer getCount() throws Exception {
        return (Integer) this.sqlSession.selectOne(namespace + ".getTotalCount");
    }

    public T getObject(Long key) throws Exception {
        return (T) this.sqlSession.selectOne(namespace + ".getObject", key);
    }

    public void create(T object) throws Exception {
        try {
            this.sqlSession.insert(namespace + ".insert", object);
        } catch (Exception e) {
            Throwable throwable = e.getCause();
            if (throwable.getMessage().toLowerCase().contains("duplicate entry")) {
                logger.error("Duplicate entry", object);
            } else {
                throw new Exception(e);
            }
        }
    }

    public void update(T object) throws Exception {
        this.sqlSession.update(namespace + ".update", object);
    }

    public void delete(Object id) throws Exception {
        this.sqlSession.delete(namespace + ".delete", id);
    }

    public T findOne(Map<String, Object> map) throws Exception {
        StringBuilder sb = new StringBuilder();
        List<Parameter> params = new ArrayList<Parameter>();
        Set<String> keys = map.keySet();
        for (String key : keys) {
            params.add(new Parameter(key, map.get(key)));
        }

        return (T) this.sqlSession.selectOne(namespace + ".findOneByMap", params);
    }

    public T findOne(T object) throws Exception {
        return (T) this.sqlSession.selectOne(namespace + ".findOneByObject", object);
    }

    public List<T> find(Map<String, Object> map) throws Exception {
        StringBuilder sb = new StringBuilder();
        List<Parameter> params = new ArrayList<Parameter>();
        Set<String> keys = map.keySet();
        for (String key : keys) {
            params.add(new Parameter(key, map.get(key)));
        }

        return this.sqlSession.selectList(namespace + ".findByMap", params);
    }

    public List<T> find(T object) throws Exception {
        return this.sqlSession.selectList(namespace + ".findByObject", object);
    }

    public List<T> findByIn(Map<String, Object> map) throws Exception {
        StringBuilder sb = new StringBuilder();
        List<Parameter> params = new ArrayList<Parameter>();
        Set<String> keys = map.keySet();
        for (String key : keys) {
            params.add(new Parameter(key, map.get(key)));
        }

        return this.sqlSession.selectList(namespace + ".findByIn", params);
    }

    public List<T> findByLike(Map<String, Object> map) throws Exception {
        StringBuilder sb = new StringBuilder();
        List<Parameter> params = new ArrayList<Parameter>();
        Set<String> keys = map.keySet();
        for (String key : keys) {
            params.add(new Parameter(key, map.get(key)));
        }

        return this.sqlSession.selectList(namespace + ".findByLike", params);
    }

    public List<T> getList(Criteria c) throws Exception {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("fieldsClause", c.getFieldClause());
        map.put("whereClause", c.getWhereClause());
        map.put("like", c.getLike());
        map.put("orderClause", c.getOrderClause());
        map.put("offset", String.valueOf(c.getStart()));
        map.put("limit", String.valueOf(c.getFetchSize()));
        return this.sqlSession.selectList(namespace + ".query", map);
    }

    public Integer getCount(Criteria c) throws Exception {
        int count = 0;

        HashMap<String, Object> map = new HashMap<String, Object>();

        map.put("whereClause", c.getWhereClause());

        count = (Integer) this.sqlSession.selectOne(namespace + ".getCount", map);

        return count;
    }

    public void batchInsert(List<T> list) throws Exception {
        this.sqlSession.insert(namespace + ".batchInsert", list);
    }
}