package com.snake.media.service;

import com.base.exception.DaoException;
import com.base.exception.ServiceException;
import com.base.service.BasicService;
import com.snake.media.dao.IVideoDao;
import com.snake.media.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wen on 2015/5/1.
 */
@Service
@Repository("videoService")
public class VideoService extends BasicService<Video> implements IVideoService {

    @Autowired
    @Qualifier("videoDao")
    private IVideoDao videoDao;

    @Override
    public IVideoDao getDao() {
        return videoDao;
    }

    public List<Video> getListLike(String filePath) throws ServiceException {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("file_path", filePath);
            return getDao().findByLike(map);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }
}
