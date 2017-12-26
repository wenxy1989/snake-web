package com.snake.media.service;

import com.base.exception.ServiceException;
import com.base.service.IBasicService;
import com.snake.media.model.Image;

import java.util.List;

/**
 * Created by wen on 2015/5/1.
 */
public interface IImageService extends IBasicService<Image> {
    List<Image> getListLike(String filePath) throws ServiceException;
}
