package com.snake.inter.service;

import com.base.exception.DaoException;
import com.base.exception.ServiceException;
import com.base.service.BasicService;
import com.snake.inter.dao.IModelDao;
import com.snake.inter.dao.IModelParameterDao;
import com.snake.inter.model.Model;
import com.snake.inter.model.ModelParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Repository
public class ModelService extends BasicService<Model> implements IModelService {

    @Autowired
    @Qualifier("modelDao")
    private IModelDao modelDao;
    
    @Autowired
    @Qualifier("modelParameterDao")
    private IModelParameterDao modelParameterDao;

    @Override
    public IModelDao getDao() {
        return modelDao;
    }

    private static ModelParameter parameter_Id;
    private static ModelParameter parameter_deleted;
    private static ModelParameter parameter_createTime;
    private static ModelParameter parameter_createId;
    private static ModelParameter parameter_createUser;
    private static ModelParameter parameter_updateTime;
    private static ModelParameter parameter_updateId;
    private static ModelParameter parameter_updateUser;

    private List<ModelParameter> getDefaultList(Long modelId,Long createId) {
        parameter_Id = new ModelParameter("ID","id","Long",11l,"11","*","主键",1);
        parameter_deleted = new ModelParameter("已删除","deleted","Integer",1l,"0","*","逻辑删除标志",0);
        parameter_createTime = new ModelParameter("创建时间","createTime","String",19l,"2016-12-27 14:59:16","*","时间格式字符串",0);
        parameter_createId = new ModelParameter("创建人ID","createId","Long",11l,"11","*","创建人ID",0);
        parameter_createUser = new ModelParameter("创建人名称","createUser","String",50l,"wenxy","*","创建人名称",0);
        parameter_updateTime = new ModelParameter("更新时间","updateTime","String",19l,"2016-12-27 15:11:55","*","更新时间",0);
        parameter_updateId = new ModelParameter("更新人ID","updateId","Long",11l,"11","*","更新人ID",0);
        parameter_updateUser = new ModelParameter("更信人名称","updateUser","String",50l,"wenxy","*","更信人名称",0);
        parameter_Id.setModelId(modelId);
        parameter_Id.setCreatorId(createId);
        parameter_deleted.setModelId(modelId);
        parameter_deleted.setCreatorId(createId);
        parameter_createId.setModelId(modelId);
        parameter_createId.setCreatorId(createId);
        parameter_createUser.setModelId(modelId);
        parameter_createUser.setCreatorId(createId);
        parameter_createTime.setModelId(modelId);
        parameter_createTime.setCreatorId(createId);
        parameter_updateId.setModelId(modelId);
        parameter_updateId.setCreatorId(createId);
        parameter_updateUser.setModelId(modelId);
        parameter_updateUser.setCreatorId(createId);
        parameter_updateTime.setModelId(modelId);
        parameter_updateTime.setCreatorId(createId);
        List<ModelParameter> list = new ArrayList<ModelParameter>();
        list.add(parameter_Id);
        list.add(parameter_deleted);
        list.add(parameter_createTime);
        list.add(parameter_createId);
        list.add(parameter_createUser);
        list.add(parameter_updateTime);
        list.add(parameter_updateId);
        list.add(parameter_updateUser);
        return list;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor ={Throwable.class })
    public void create(Model object) throws ServiceException {
        try {
            getDao().create(object);
            List<ModelParameter> list = getDefaultList(object.getId(),object.getCreatorId());
            modelParameterDao.batchInsert(list);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Model getObjectByCode(String code) throws ServiceException {
        try {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("code_",code);
            return modelDao.findOne(map);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    public List<Model> getListByApplicationId(Long applicationId) throws ServiceException {
        try {
            return modelDao.getListByApplicationId(applicationId);
        }catch (DaoException e){
            throw new ServiceException(e);
        }
    }
}