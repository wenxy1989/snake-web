package com.snake.chinese.service;

import com.base.exception.DaoException;
import com.base.exception.ServiceException;
import com.base.service.BasicService;
import com.snake.chinese.utils.WordUtils;
import com.snake.chinese.dao.IStructureDao;
import com.snake.chinese.dao.IStructureElementDao;
import com.snake.chinese.model.Structure;
import com.snake.chinese.model.relation.StructureElement;
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
@Repository("structureService")
public class StructureService extends BasicService<Structure> implements IStructureService {

    @Autowired
    @Qualifier("structureDao")
    private IStructureDao elementDao;
    @Autowired
    @Qualifier("structureElementDao")
    private IStructureElementDao structureElementDao;

    @Override
    public IStructureDao getDao() {
        return elementDao;
    }

    public List<StructureElement> getStructureElementListByStructureId(Long structureId) throws ServiceException {
        try {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("structure_id",structureId);
            List<StructureElement> list = structureElementDao.find(map);
            for(StructureElement obj: list){
                obj.setElement(WordUtils.getElement(obj.getElementId()));
            }
            return list;
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    public void addStructureElement(StructureElement obj) throws ServiceException {
        try{
            structureElementDao.create(obj);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    public void removeStructureElement(StructureElement obj) throws ServiceException {
        try{
            structureElementDao.removeObject(obj);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }
}
