package com.module.novel.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.common.dao.Dao;
import com.base.common.service.AbstractService;
import com.module.novel.model.Equipment;

@Service("equipmentService")
public class EquipmentService extends AbstractService<Equipment> implements IEquipmentService {

	@Resource(name="dao")
	private Dao dao;
	
	@Override
	protected Dao getDao() {
		return dao;
	}

}
