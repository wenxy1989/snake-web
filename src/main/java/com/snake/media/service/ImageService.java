package com.snake.media.service;

import com.base.exception.DaoException;
import com.base.exception.ServiceException;
import com.base.service.BasicService;
import com.base.util.Criteria;
import com.snake.media.dao.IImageDao;
import com.snake.media.model.Image;
import com.snake.media.model.ImagePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wen on 2015/5/1.
 */
@Service
@Repository("imageService")
public class ImageService extends BasicService<Image> implements IImageService {

    @Autowired
    @Qualifier("imageDao")
    private IImageDao imageDao;

    @Override
    public IImageDao getDao() {
        return imageDao;
    }

    public List<Image> getListLike(String filePath) throws ServiceException {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("file_path", filePath);
            return getDao().findByLike(map);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public ImagePage<Image> getList(Criteria c) throws ServiceException {
        try {
            int count = getCount(c);
            List<Image> list = getDao().getList(c);

            final Criteria cri = c;

            ImagePage<Image> page = new ImagePage<Image>(list, new Pageable() {
                public int getPageNumber() {
                    return cri.getPageNo();
                }

                public int getPageSize() {
                    return cri.getFetchSize();
                }

                public int getOffset() {
                    return cri.getStart();
                }

                public Sort getSort() {
                    List<String> orders = cri.getOrder();
                    if (orders.size() == 0) return null;

                    String order = orders.get(0);
                    String[] splits = order.split(" ");
                    if (splits.length > 1) {
                        if ("asc".equals(splits[1])) {
                            return new Sort(Sort.Direction.ASC, splits[0]);
                        } else {
                            return new Sort(Sort.Direction.DESC, splits[0]);
                        }
                    } else {
                        return new Sort(splits[0]);
                    }
                }
            }, count);
            page.setStart(c.getStart());

            return page;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
