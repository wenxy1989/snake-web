package com.module.config.model;

import com.base.common.model.AbstractModel;
import com.base.utils.StringUtils;
import com.module.config.ParseSQL;

import java.io.Serializable;
import java.util.List;

/**
 * 对象 Obj
 * @author wenxy
 * @version 1.00 ,Date: 2014-03-16 16:56:38
 */
public class Obj extends AbstractModel implements ParseSQL {

	public String parseSQL() {
		StringBuffer sb = new StringBuffer("drop table if exists ");
		sb.append(this.getTable()).append(";\n");
		sb.append("create table ").append(this.getTable()).append("(");
		for(Attr attr:this.attrs){
			String sql = attr.parseSQL();
			sb.append(sql);
		}
		for(Uniq unique:this.uniqs){
			String sql = unique.parseSQL();
			sb.append(sql);
		}
		if((null != this.attrs && this.attrs.size() > 0) || null != this.uniqs && this.uniqs.size() > 0){
			sb.deleteCharAt(sb.length()-1);
		}
		sb.append(")engine=innodb charset=utf8 comment='").append(this.getName()).append("';");
		return sb.toString();
	}
	
	private Long applicationId;//所属应用id
	private String code;//所属英文代码
	private String table;//表名：默认值:[app]_[code]
	private App app;//所属应用
	private List<Attr> attrs;
	private List<Uniq> uniqs;

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public String getCode() {
		if(StringUtils.isEmpty(this.code) && StringUtils.notEmpty(this.table)){
            StringBuffer sb = new StringBuffer();
			String[] arr = this.table.split("_");
            if(arr.length > 1){
                for(int i=1;i< arr.length;i++) {
                    String str = arr[i];
                    sb.append(StringUtils.getFirstLarge(str));
                }
                this.code = sb.toString();
            }else{
                this.code = StringUtils.getFirstLarge(arr[0]).toString();
            }
		}
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTable() {
		if(StringUtils.isEmpty(this.table) && this.app != null){
			this.table = this.app.getCode().toLowerCase() + "_" +this.getCode().toLowerCase();
		}
		return this.table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public App getApp() {
		return app;
	}

	public void setApp(App app) {
		this.app = app;
	}

	public List<Attr> getAttrs() {
		return attrs;
	}

	public void setAttrs(List<Attr> attrs) {
		this.attrs = attrs;
	}

	public List<Uniq> getUniqs() {
		return uniqs;
	}

	public void setUniqs(List<Uniq> uniqs) {
		this.uniqs = uniqs;
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