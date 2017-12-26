package com.snake.media.dao;

import com.base.dao.MybatisBasicDao;
import com.snake.media.model.Image;
import org.springframework.stereotype.Repository;

/**
 * Created by wen on 2015/5/1.
 */
@Repository("imageDao")
public class ImageDao extends MybatisBasicDao<Image> implements IImageDao {

    public ImageDao() {
        super(Image.class);
    }
}
