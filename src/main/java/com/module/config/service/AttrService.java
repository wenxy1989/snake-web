package com.module.config.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.common.dao.Dao;
import com.base.common.service.AbstractService;
import com.module.config.model.Attr;

@Service("attrService")
public class AttrService extends AbstractService<Attr> implements IAttrService {
	
	@Resource(name="dao")
	private Dao dao;

	@Override
	protected Dao getDao() {
		return dao;
	}

	@SuppressWarnings("unchecked")
	public List<Attr> getListByObjectId(Long id) {
		List<Attr> list = null;
		try{
			list = dao.find("from Attr a where a.objId=?", id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

}
