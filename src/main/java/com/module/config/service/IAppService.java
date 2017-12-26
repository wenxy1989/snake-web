package com.module.config.service;

import com.base.common.service.BaseService;
import com.module.config.model.App;


public interface IAppService extends BaseService<App> {

	public void createFile(App app);

}