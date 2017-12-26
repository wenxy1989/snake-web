package com.module.config.service;

import com.base.common.dao.Dao;
import com.base.common.service.AbstractService;
import com.base.utils.FreeMarkerUtils;
import com.module.config.model.Obj;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("objService")
public class ObjService extends AbstractService<Obj> implements IObjService {
	
	@Resource(name="dao")
	private Dao dao;

	@Override
	protected Dao getDao() {
		return dao;
	}

	@SuppressWarnings("unchecked")
	public List<Obj> getListByApplicationId(Long applicationId) {
		List<Obj> list = null;
		try{
			list = dao.find("from Obj a where a.applicationId=?", applicationId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	public void createFiles(Obj obj) {
		String app = obj.getApp().getCode();
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("obj",obj);
    	map.put("application",app);
    	map.put("module",obj.getCode());
    	map.put("attributes", obj.getAttrs());
    	String basePath ="java/com."+app+".@{little}.";
    	FreeMarkerUtils.getInstance().buildTemplate("java/model.java.ftl", obj.getCode(), map, basePath + "model/", "@{large}.java");
    	FreeMarkerUtils.getInstance().buildTemplate("java/controller.java.ftl", obj.getCode(), map, basePath + "controller/", "@{large}Controller.java");
    	FreeMarkerUtils.getInstance().buildTemplate("java/iservice.java.ftl", obj.getCode(), map, basePath + "service/", "I@{large}Service.java");
    	FreeMarkerUtils.getInstance().buildTemplate("java/service.java.ftl", obj.getCode(), map, basePath + "service/", "@{large}Service.java");
    	FreeMarkerUtils.getInstance().buildTemplate("java/idao.java.ftl", obj.getCode(), map, basePath + "dao/", "I@{large}Dao.java");
    	FreeMarkerUtils.getInstance().buildTemplate("java/dao.java.ftl", obj.getCode(), map, basePath + "dao/", "@{large}Dao.java");
    	basePath ="webapp/jsp/"+app+"/@{little}/";
    	FreeMarkerUtils.getInstance().buildTemplate("page/listjsp.ftl", obj.getCode(), map, basePath, "list.jsp");
    	FreeMarkerUtils.getInstance().buildTemplate("page/editjsp.ftl", obj.getCode(), map, basePath, "edit.jsp");
    	FreeMarkerUtils.getInstance().buildTemplate("page/addjsp.ftl", obj.getCode(), map, basePath, "add.jsp");
    	FreeMarkerUtils.getInstance().buildTemplate("config/ibatis.ftl", obj.getCode(), map, "resources/sqlmap/", "@{large}Mapper.xml");
	}
}
