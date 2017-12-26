package com.base.common.service;

import java.io.Serializable;
import java.util.List;

public interface BaseService<T> {
	
	public void create(T object);
	
	public void update(T object);
	
	public void delete(T object);
	
	public List<T> getAll(Class<T> clazz);
	
	public List<T> getList(T object);
	
	public T getObject(Class<T> clazz,Serializable id);

}
