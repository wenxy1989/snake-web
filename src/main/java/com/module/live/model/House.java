package com.module.live.model;

import java.io.Serializable;

import com.base.common.model.AbstractModel;
/**
 * 房屋信息模块实体类 House
 * @author wenxy
 * @version 1.00 ,Date: 2014-03-16 16:56:38
 */
public class House extends AbstractModel{

	private Long price;//价格
	private String type;//房屋类型
	private Long area;//房屋面积
	private String position;//位置

	/**
	 * 价格
	 * get value of Price
	 * @return type Long
	 **/
	public Long getPrice(){
		return this.price;
	}
	
	/**
	 * 价格
	 * set value of Price
	 * @param type Long
	 **/
	public void setPrice(Long price){
		this.price = price;
	}
	/**
	 * 房屋类型
	 * get value of Type
	 * @return type String
	 **/
	public String getType(){
		return this.type;
	}
	
	/**
	 * 房屋类型
	 * set value of Type
	 * @param type String
	 **/
	public void setType(String type){
		this.type = type;
	}
	/**
	 * 房屋面积
	 * get value of Area
	 * @return type Long
	 **/
	public Long getArea(){
		return this.area;
	}
	
	/**
	 * 房屋面积
	 * set value of Area
	 * @param type Long
	 **/
	public void setArea(Long area){
		this.area = area;
	}
	/**
	 * 位置
	 * get value of Position
	 * @return type String
	 **/
	public String getPosition(){
		return this.position;
	}
	
	/**
	 * 位置
	 * set value of Position
	 * @param type String
	 **/
	public void setPosition(String position){
		this.position = position;
	}

	@Override
	public Serializable getObjectId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUniqueIdName() {
		// TODO Auto-generated method stub
		return null;
	}

}