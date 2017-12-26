package com.module.config.service;

import com.base.common.dao.Dao;
import com.base.common.service.AbstractService;
import com.base.utils.FreeMarkerUtils;
import com.module.config.model.App;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service("appService")
public class AppService extends AbstractService<App> implements IAppService {
	
	@Resource(name="dao")
	private Dao dao;

	@Override
	protected Dao getDao() {
		return dao;
	}

	public void createFile(App app) {
		String appName = app.getCode();
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("app",app);
    	FreeMarkerUtils.getInstance().buildTemplate("sql/createsql.ftl", appName, map, "sql/", "@{little}.sql");
	}

}
