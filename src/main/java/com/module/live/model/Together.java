package com.module.live.model;

import com.base.common.model.AbstractModel;

import java.io.Serializable;
/**
 * 房屋合租模块实体类 Together
 * @author wenxy
 * @version 1.00 ,Date: 2014-03-16 13:17:28
 */
public class Together extends AbstractModel{

	private String address;//意向居住地
	private Long averagePrice;//自身承受房租大概值
	private Long tenPrice;//理想居住地十平米平均价格
	private Integer sex;//性别限制

	/**
	 * 意向居住地
	 * get value of Address
	 * @return type String
	 **/
	public String getAddress(){
		return this.address;
	}
	
	/**
	 * 意向居住地
	 * set value of Address
	 * @param address String
	 **/
	public void setAddress(String address){
		this.address = address;
	}
	/**
	 * 自身承受房租大概值
	 * get value of AveragePrice
	 * @return type Long
	 **/
	public Long getAveragePrice(){
		return this.averagePrice;
	}
	
	/**
	 * 自身承受房租大概值
	 * set value of AveragePrice
	 * @param averagePrice Long
	 **/
	public void setAveragePrice(Long averagePrice){
		this.averagePrice = averagePrice;
	}
	/**
	 * 理想居住地十平米平均价格
	 * get value of TenPrice
	 * @return type Long
	 **/
	public Long getTenPrice(){
		return this.tenPrice;
	}
	
	/**
	 * 理想居住地十平米平均价格
	 * set value of TenPrice
	 * @param tenPrice Long
	 **/
	public void setTenPrice(Long tenPrice){
		this.tenPrice = tenPrice;
	}
	/**
	 * 性别限制
	 * get value of Sex
	 * @return type Integer
	 **/
	public Integer getSex(){
		return this.sex;
	}
	
	/**
	 * 性别限制
	 * set value of Sex
	 * @param sex Integer
	 **/
	public void setSex(Integer sex){
		this.sex = sex;
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