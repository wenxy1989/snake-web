package com.snake.chinese.service;

import com.base.exception.DaoException;
import com.base.exception.ServiceException;
import com.base.service.BasicService;
import com.snake.chinese.dao.ITypeDao;
import com.snake.chinese.model.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wen on 2015/5/1.
 */
@Service
@Repository("typeService")
public class TypeService extends BasicService<Type> implements ITypeService {

    @Autowired
    @Qualifier("typeDao")
    private ITypeDao typeDao;

    @Override
    public ITypeDao getDao() {
        return typeDao;
    }

    public List<Type> getListByWordId(Long wordId) throws ServiceException {
        try{
            return this.typeDao.getListByWordId(wordId);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }
}
