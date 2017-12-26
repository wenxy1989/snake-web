package com.base.common.service;

import com.base.common.dao.Dao;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractService<T> implements BaseService<T> {
	
	protected abstract Dao getDao();
	
	public void create(T object) {
		try{
			getDao().save(object);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void update(T object) {
		try{
			getDao().update(object);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void delete(T object) {
		try{
			getDao().delete(object);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll(Class<T> clazz) {
		List<T> list = null;
		try{
			list = getDao().loadAll(clazz);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getList(T object){
		List<T> list = null;
		try{
			list = getDao().findByExample(object);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public T getObject(Class<T> clazz, Serializable id) {
		T object = null;
		try{
			if(null != id){
				object = (T) getDao().get(clazz, id);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return object;
	}

}
