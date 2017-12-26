package com.snake.media.service;

import com.base.exception.ServiceException;
import com.base.service.IBasicService;
import com.snake.media.model.Video;

import java.util.List;

/**
 * Created by wen on 2015/5/1.
 */
public interface IVideoService extends IBasicService<Video> {
    List<Video> getListLike(String filePath) throws ServiceException;
}
