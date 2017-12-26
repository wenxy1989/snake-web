package com.module.config.service;

import com.base.common.service.BaseService;
import com.module.config.model.Obj;

import java.util.List;


public interface IObjService extends BaseService<Obj> {

	public List<Obj> getListByApplicationId(Long applicationId);

	public void createFiles(Obj obj);

}