package com.snake.template.dao;

import com.base.dao.MybatisBasicDao;
import com.base.util.HashMaps;
import com.snake.template.model.TemplateConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wenxy on 2016/11/29.
 */
public class TemplateDao extends MybatisBasicDao<TemplateConfig> implements ITemplateDao {

    public TemplateDao() {
        super(TemplateConfig.class);
    }

    public List<TemplateConfig> selectListByFrameId(Long frameId) throws Exception {
        return super.find(HashMaps.build(String.class,Object.class).add("frame_id",frameId));
    }
}
