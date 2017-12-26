package com.snake.mysql.model;

import com.snake.mysql.ParseSQL;

import java.util.List;

/**
 * 数据库
 * @author wenxy
 * @version 1.00 ,Date: 2016-12-1 11:10:28
 */
public class Database implements ParseSQL {

	private Long id;
	private String name;//数据库中文名称
	private String remark;
	private String code;//数据库名称
	private String address;//地址
	private Integer port;//端口
	private String username;//用户名
	private String password;//密码
	private String createdTime;
	private Long creatorId;
	private List<Table> tables;

	public String parseSQL() {
		StringBuffer sb = new StringBuffer("create database ");
		sb.append(this.code);
		sb.append(" engin=innodb comment=\"");
		sb.append(this.remark);
		sb.append("\"");
		return sb.toString();
	}

	public Database clone(){
        Database obj = new Database();
        obj.setName(this.name);
        obj.setRemark(this.remark);
        obj.setCode(this.code);
        obj.setAddress(this.address);
        obj.setPort(this.port);
        obj.setUsername(this.username);
        obj.setPassword(this.password);
        return obj;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public List<Table> getTables() {
		return tables;
	}

	public void setTables(List<Table> tables) {
		this.tables = tables;
	}
}