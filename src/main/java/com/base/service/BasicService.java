package com.base.service;

import com.base.dao.IBasicDao;
import com.base.exception.DaoException;
import com.base.exception.ServiceException;
import com.base.util.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * User: qufengfu
 * Date: 13-7-6
 */
public abstract class BasicService<T> implements IBasicService<T> {
    protected transient final Logger logger = LoggerFactory.getLogger(getClass());

    public abstract IBasicDao<T> getDao();

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<T> getAll() throws ServiceException {
        try {
            return getDao().getAll();
        } catch (DaoException e) {
            throw new ServiceException("ERROR:", e);
        } catch (Exception e) {
            throw new ServiceException("ERROR:", e);
        }
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<T> getList(int first, int limit) throws ServiceException {
        try {
            return getDao().getList(first, limit);
        } catch (DaoException e) {
            throw new ServiceException("ERROR:", e);
        } catch (Exception e) {
            throw new ServiceException("ERROR:", e);
        }
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Integer getCount() throws ServiceException {
        try {
            return getDao().getCount();
        } catch (DaoException e) {
            throw new ServiceException("ERROR:", e);
        } catch (Exception e) {
            throw new ServiceException("ERROR:", e);
        }
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public T getObject(Long key) throws ServiceException {
        try {
            return getDao().getObject(key);
        } catch (DaoException e) {
            throw new ServiceException("ERROR:", e);
        } catch (Exception e) {
            throw new ServiceException("ERROR:", e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Throwable.class})
    public void create(T object) throws ServiceException {
        try {
            getDao().create(object);
        } catch (DaoException e) {
            throw new ServiceException("ERROR:", e);
        } catch (Exception e) {
            throw new ServiceException("ERROR:", e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Throwable.class})
    public void update(T object) throws ServiceException {
        try {
            getDao().update(object);
        } catch (DaoException e) {
            throw new ServiceException("ERROR:", e);
        } catch (Exception e) {
            throw new ServiceException("ERROR:", e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Throwable.class})
    public void delete(Object id) throws ServiceException {
        try {
            getDao().delete(id);
        } catch (DaoException e) {
            throw new ServiceException("ERROR:", e);
        } catch (Exception e) {
            throw new ServiceException("ERROR:", e);
        }
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public T findOne(Map<String, Object> map) throws ServiceException {
        try {
            return getDao().findOne(map);
        } catch (DaoException e) {
            throw new ServiceException("ERROR:", e);
        } catch (Exception e) {
            throw new ServiceException("ERROR:", e);
        }
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<T> find(Map<String, Object> map) throws ServiceException {
        try {
            return getDao().find(map);
        } catch (DaoException e) {
            throw new ServiceException("ERROR:", e);
        } catch (Exception e) {
            throw new ServiceException("ERROR:", e);
        }
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<T> findByIn(Map<String, Object> map) throws ServiceException {
        try {
            return getDao().findByIn(map);
        } catch (DaoException e) {
            throw new ServiceException("ERROR:", e);
        } catch (Exception e) {
            throw new ServiceException("ERROR:", e);
        }
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<T> findByLike(Map<String, Object> map) throws ServiceException {
        try {
            return getDao().findByLike(map);
        } catch (DaoException e) {
            throw new ServiceException("ERROR:", e);
        } catch (Exception e) {
            throw new ServiceException("ERROR:", e);
        }
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<T> getList(Criteria c) throws ServiceException {
        try {
            int count = getDao().getCount(c);
            List<T> list = getDao().getList(c);
            final Criteria cri = c;
            Page<T> page = new PageImpl<T>(list, new Pageable() {
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
            return page;
        } catch (DaoException e) {
            throw new ServiceException("ERROR:", e);
        } catch (Exception e) {
            throw new ServiceException("ERROR:", e);
        }
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<T> getPageList(Criteria c) throws ServiceException {
        try {
            return getDao().getList(c);
        } catch (DaoException e) {
            throw new ServiceException("ERROR:", e);
        } catch (Exception e) {
            throw new ServiceException("ERROR:", e);
        }
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Integer getCount(Criteria c) throws ServiceException {
        try {
            return getDao().getCount(c);
        } catch (DaoException e) {
            throw new ServiceException("ERROR:", e);
        } catch (Exception e) {
            throw new ServiceException("ERROR:", e);
        }
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public T findOne(T object) throws ServiceException {
        try {
            return (T) getDao().findOne(object);
        } catch (DaoException e) {
            throw new ServiceException("ERROR:", e);
        } catch (Exception e) {
            throw new ServiceException("ERROR:", e);
        }
    }

    /**
     * 根据对象进行列表查询
     *
     * @param object 查询的对象
     * @return 符合条件的对象列表
     * @throws com.base.exception.ServiceException
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<T> find(T object) throws ServiceException {
        try {
            return getDao().find(object);
        } catch (DaoException e) {
            throw new ServiceException("ERROR:", e);
        } catch (Exception e) {
            throw new ServiceException("ERROR:", e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Throwable.class})
    public void batchInsert(List<T> list) throws ServiceException {
        int size = 100;
        int count = 0;
        try {
            if (null != list && list.size() > 0) {
                count = list.size();
                int page = count / size;
                if (count % size > 0) {
                    page++;
                }
                for (int i = 0; i < page; i++) {
                    int start = i * size;
                    int end = start + size;
                    if (end > count) {
                        end = count;
                    }
                    List<T> each = list.subList(start, end);
                    getDao().batchInsert(each);
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}