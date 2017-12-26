package com.snake.media.dao;

import com.base.dao.MybatisBasicDao;
import com.snake.media.model.Video;
import org.springframework.stereotype.Repository;

/**
 * Created by wen on 2015/5/1.
 */
@Repository("videoDao")
public class VideoDao extends MybatisBasicDao<Video> implements IVideoDao {

    public VideoDao() {
        super(Video.class);
    }
}
