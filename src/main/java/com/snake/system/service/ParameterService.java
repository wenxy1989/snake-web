package com.snake.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.common.dao.Dao;
import com.base.common.model.AbstractModel;
import com.base.common.service.AbstractService;
import com.snake.system.model.Parameter;

@Service("parameterService")
public class ParameterService extends AbstractService<Parameter> implements IParameterService {
	
	@Resource(name="dao")
	private Dao dao;
	
	private static Map<String,Parameter> uniqueIdParameters = null;

	@Override
	protected Dao getDao() {
		return dao;
	}
	
	public Long getLongValue(String key){
		Long value = null;
		Parameter parameter = (Parameter) dao.get(Parameter.class, key);
		if(null != parameter){
			value = parameter.getLongValue();
		}
		return value;
	}
	
	public String getStringValue(String key){
		String value = null;
		Parameter parameter = (Parameter) dao.get(Parameter.class, key);
		if(null != parameter){
			value = parameter.getStringValue();
		}
		return value;
	}
	
	/**
	 * 获取唯一id
	 * @param name 域
	 * 同一个域的id是唯一的
	 */
	protected synchronized Long getUniqueId(String name) {
		if(null == name && "".equals(name)){
			name = "sys";
		}
		name += "_" + Parameter.KEY_UNIQUE_ID;
		if(null == uniqueIdParameters){
			uniqueIdParameters = new HashMap<String,Parameter>();
		}
		Parameter uniqueIdParameter = uniqueIdParameters.get(name);
		if(null == uniqueIdParameter){
			List<Parameter> list = dao.find("from Parameter p where p.key=?",name);
			if(null != list && list.size() > 0){
				uniqueIdParameter = list.get(0);
			}
			if(null == uniqueIdParameter){
				uniqueIdParameter = Parameter.getUniqueIdParameter(name);
				dao.save(uniqueIdParameter);
			}
			uniqueIdParameters.put(name, uniqueIdParameter);
		}
		Long uniqueId = new Long(uniqueIdParameter.getLongValue());
		uniqueIdParameter.setLongValue(uniqueIdParameter.getLongValue() +1);
		dao.update(uniqueIdParameter);
		return uniqueId;
	}
	
	@Override
	public synchronized void update(Parameter parameter){
		super.update(parameter);
	}

	@Override
	public synchronized void delete(Parameter parameter){
		super.delete(parameter);
	}

	public void setUniqueId(AbstractModel model) {
		Long id = this.getUniqueId(model.getUniqueIdName());
		model.setId(id);
	}
	
}
