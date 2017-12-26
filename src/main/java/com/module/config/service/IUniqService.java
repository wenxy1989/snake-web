package com.module.config.service;

import com.base.common.service.BaseService;
import com.module.config.model.Uniq;

import java.util.List;


public interface IUniqService extends BaseService<Uniq> {

	public List<Uniq> getListByObjectId(Long id);

}