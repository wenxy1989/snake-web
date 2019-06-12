package com.snake.inter.dao;

import com.base.dao.MybatisBasicDao;
import com.snake.inter.dao.IResultDao;
import com.snake.inter.model.Result;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("resultDao")
public class ResultDao extends MybatisBasicDao<Result> implements IResultDao {

    public ResultDao() {
        super(Result.class);
    }

    public List<Result> getListByUrlId(Long modelId) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("url_id", modelId);
        return find(map);
    }
}