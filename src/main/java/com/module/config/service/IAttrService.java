package com.module.config.service;

import java.util.List;

import com.base.common.service.BaseService;
import com.module.config.model.Attr;


public interface IAttrService extends BaseService<Attr> {

	public List<Attr> getListByObjectId(Long id);

}