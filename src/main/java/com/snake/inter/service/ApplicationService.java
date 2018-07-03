package com.snake.inter.service;

import com.base.exception.DaoException;
import com.base.exception.ServiceException;
import com.base.service.BasicService;
import com.snake.inter.dao.*;
import com.snake.inter.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Repository
public class ApplicationService extends BasicService<Application> implements IApplicationService {

    @Autowired
    @Qualifier("applicationDao")
    private IApplicationDao applicationDao;

    @Autowired
    @Qualifier("modelDao")
    private IModelDao modelDao;

    @Autowired
    @Qualifier("i_groupDao")
    private IGroupDao groupDao;

    @Autowired
    @Qualifier("urlDao")
    private IUrlDao urlDao;

    @Autowired
    @Qualifier("modelParameterDao")
    private IModelParameterDao modelParameterDao;

    @Autowired
    @Qualifier("uploadDao")
    private IUploadDao uploadDao;

    @Autowired
    @Qualifier("resultDao")
    private IResultDao resultDao;


    @Override
    public IApplicationDao getDao() {
        return applicationDao;
    }

    public Application getObjectByCode(String code) throws ServiceException {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("code_", code);
            return applicationDao.findOne(map);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Application getDetails(Long id) throws ServiceException {
        try {
            Application application = getDao().getObject(id);
            if (null != application) {
                List<Model> modelList = modelDao.getListByApplicationId(application.getId());
                if (null != modelList && modelList.size() > 0) {
                    for(Model model : modelList){
                        List<ModelParameter> parameterList = modelParameterDao.getListByModelId(model.getId());
                        model.setParameterList(parameterList);
                    }
                    application.setModelList(modelList);
                }
                List<Group> groupList = groupDao.getListByApplicationId(application.getId());
                if (null != groupList && groupList.size() > 0) {
                    for(Group group : groupList){
                        List<Url> urlList = urlDao.getListByGroupId(group.getId());
                        if (null != urlList && urlList.size() > 0) {
                            for(Url url : urlList){
                                List uploadList = uploadDao.getListByUrlId(url.getId());
                                url.setUploadParameters(uploadList);
                                List resultList = resultDao.getListByUrlId(url.getId());
                                url.setResultParameters(resultList);
                            }
                            application.setUrlList(urlList);
                        }
                        group.setUrlList(urlList);
                    }
                    application.setGroupList(groupList);
                }
            }
            return application;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}