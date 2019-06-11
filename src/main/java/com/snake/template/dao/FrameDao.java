package com.snake.template.dao;

import com.snake.resource.dao.AbstractResourceDao;
import com.snake.template.model.Frame;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HP on 2018/7/3.
 */
@Repository("frameDao")
public class FrameDao extends AbstractResourceDao<Frame> implements IFrameDao {

    private static Map<Object, Frame> map = new HashMap<Object, Frame>();

    public FrameDao() {
        super(Frame.class);
    }

    public final static Object getFinalList() {
        return map.values();
    }

    public final static Object getFinalObject(Long id) {
        return map.get(id);
    }

    @Override
    public Map<Object, Frame> getMap() {
        return map;
    }
}
