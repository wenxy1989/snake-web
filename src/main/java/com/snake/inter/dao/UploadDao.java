package com.snake.inter.dao;

import com.base.dao.MybatisBasicDao;
import com.snake.inter.dao.IUploadDao;
import com.snake.inter.model.Upload;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("uploadDao")
public class UploadDao extends MybatisBasicDao<Upload> implements IUploadDao {

    public UploadDao() {
        super(Upload.class);
    }

    public List<Upload> getListByUrlId(Long modelId) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("url_id", modelId);
        return find(map);
    }
}