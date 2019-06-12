package com.snake.inter.service;
import com.base.service.BasicService;
import com.snake.freemarker.FreeMarkerUtils;
import com.snake.inter.dao.*;
import com.snake.inter.model.*;
import com.snake.template.dao.ITemplateDao;
import com.snake.template.model.TemplateConfig;
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
    @Qualifier("groupDao")
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

    @Autowired
    @Qualifier("templateDao")
    private ITemplateDao templateDao;


    @Override
    public IApplicationDao getDao() {
        return applicationDao;
    }

    public Application getObjectByCode(String code) throws Exception {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("code_", code);
            return applicationDao.findOne(map);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void write(Long id, Long frameId) throws Exception {
        try {
            Application application = getDao().getObject(id);
            if (null != application) {
                List<TemplateConfig> configList = templateDao.selectListByFrameId(frameId);
                if (null != configList && configList.size() > 0) {
                    FreeMarkerUtils.getInstance().writeApplication(application, configList);
                    List<Model> modelList = modelDao.getListByApplicationId(application.getId(), 3);
                    if (null != modelList && modelList.size() > 0) {
                        for (Model model : modelList) {
                            List<ModelParameter> parameterList = modelParameterDao.getListByModelId(model.getId());
                            model.setParameterList(parameterList);
                            FreeMarkerUtils.getInstance().writeModel(application, model, configList);
                        }
                    }
                }
                /*List<Group> groupList = groupDao.getListByApplicationId(application.getId());
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
                }*/
            }
        } catch (Exception e) {
            throw e;
        }
    }
}