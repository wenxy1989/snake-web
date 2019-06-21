package com.snake.inter.service;

import com.base.service.BasicService;
import com.base.util.FileTools;
import com.base.util.StringTools;
import com.snake.freemarker.FreeMarkerWriter;
import com.snake.inter.dao.*;
import com.snake.inter.model.*;
import com.snake.template.dao.IFrameDao;
import com.snake.template.dao.ITemplateDao;
import com.snake.template.model.Frame;
import com.snake.template.model.TemplateConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

  @Autowired
  @Qualifier("frameDao")
  private IFrameDao frameDao;


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

  public static List<TemplateConfig> buildTemplateList(String folder) {
    List<String> fileList = FileTools.fileListTree(folder);
    if (null != fileList) {
      List<TemplateConfig> templateConfigList = new ArrayList<TemplateConfig>();
      for (String fileRealPath : fileList) {
        String filePath = fileRealPath.replaceAll(folder, "");
        String fileFolder = filePath.substring(0, filePath.lastIndexOf("/"));
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
        TemplateConfig config = new TemplateConfig();
        config.setGroup(fileFolder);
        config.setName(fileName);
        config.setSavePathModel(StringTools.fileModel(fileFolder));
        config.setSaveFileModel(StringTools.fileModel(fileName));
        if (StringTools.modelTemplate(filePath)) {
          config.setUpdateType(0);
        } else {
          config.setUpdateType(1);
        }
        config.setType(StringTools.templateType(filePath));
        templateConfigList.add(config);
      }
      return templateConfigList;
    }
    return null;
  }

  public void write(Long id, Long frameId) throws Exception {
    try {
      Application application = getDao().getObject(id);
      Frame frame = frameDao.getObject(frameId);
      if (null != application && null != frame) {
        List<TemplateConfig> configList = buildTemplateList(frame.getFolder());
        if (null == configList) {
          configList = templateDao.selectListByFrameId(frameId);
        }

        if (null != configList && configList.size() > 0) {
          FreeMarkerWriter marker = new FreeMarkerWriter(frame.getFolder());
          List<Model> modelList = modelDao.getListByApplicationId(application.getId(), 3);
          application.setModelList(modelList);
          marker.writeApplication(application, configList);
          if (null != modelList && modelList.size() > 0) {
            for (Model model : modelList) {
              List<ModelParameter> parameterList = modelParameterDao.getListByModelId(model.getId());
              model.setParameterList(parameterList);
              marker.writeModel(application, model, configList);
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