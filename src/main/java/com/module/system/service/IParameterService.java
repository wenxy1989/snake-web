package com.module.system.service;

import com.base.common.model.AbstractModel;
import com.base.common.service.BaseService;
import com.module.system.model.Parameter;

public interface IParameterService extends BaseService<Parameter> {
	
	public void setUniqueId(AbstractModel model);

}
