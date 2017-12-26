package com.module.config.model;

import com.base.common.model.AbstractModel;
import com.base.utils.StringUtils;

import java.io.Serializable;
import java.util.List;
/**
 * 应用 App
 * @author wenxy
 * @version 1.00 ,Date: 2014-03-16 16:56:38
 */
public class App extends AbstractModel{

	private String code;//数据库英文名：手填

	private String database;//数据库名称：生成规则[code]_db
	private String url;//地址
	private Integer port;//端口
	private String loginName;//用户名
	private String password;//密码
	private List<Obj> objs;

    public App clone(){
        App obj = new App();
        obj.setCode(this.code);
        obj.setDatabase(this.database);
        obj.setUrl(this.url);
        obj.setPort(this.port);
        obj.setLoginName(this.loginName);
        obj.setPassword(this.password);
        return obj;
    }
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDatabase() {
		if(StringUtils.isEmpty(this.database) && !StringUtils.isEmpty(this.code)){
			this.database = this.code.toLowerCase()+"_db";
		}
		return this.database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Obj> getObjs() {
		return objs;
	}

	public void setObjs(List<Obj> objs) {
		this.objs = objs;
	}

	@Override
	public String getUniqueIdName() {
		return "app_id";
	}

	@Override
	public Serializable getObjectId() {
		return getId();
	}

}