package com.module.config.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.common.dao.Dao;
import com.base.common.service.AbstractService;
import com.module.config.model.Uniq;

@Service("uniqService")
public class UniqService extends AbstractService<Uniq> implements IUniqService {
	
	@Resource(name="dao")
	private Dao dao;

	@Override
	protected Dao getDao() {
		return dao;
	}

	@SuppressWarnings("unchecked")
	public List<Uniq> getListByObjectId(Long id) {
		List<Uniq> list = null;
		try{
			list = dao.find("from Uniq a where a.objId=?", id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

}
