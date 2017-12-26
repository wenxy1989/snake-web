package com.snake.mysql.model;

import com.snake.mysql.ParseSQL;

import java.util.List;

/**
 * 表
 * @author wenxy
 * @version 1.00 ,Date: 2016-12-1 11:10:28
 */
public class Table implements ParseSQL {

    private Long id;
	private Long databaseId;//所属应用id
	private String name;
	private String code;//所属英文代码
    private String comment;
    private Database database;//所属应用
	private List<Column> columns;
	private List<Unique> uniques;

    private String createdTime;
    private Long creatorId;

    public Table(){}

    public Table(String name){
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(Long databaseId) {
        this.databaseId = databaseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public List<Unique> getUniques() {
        return uniques;
    }

    public void setUniques(List<Unique> uniques) {
        this.uniques = uniques;
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

    public String getCode() {
		return code;
	}

    public String parseSQL() {
        StringBuffer sb = new StringBuffer("drop table if exists ");
        sb.append(this.getCode()).append(";\n");
        sb.append("create table ").append(this.getCode()).append("(");
        for(Column column :this.columns){
            String sql = column.parseSQL();
            sb.append(sql);
        }
        for(Unique unique:this.uniques){
            String sql = unique.parseSQL();
            sb.append(sql);
        }
        if((null != this.columns && this.columns.size() > 0) || null != this.uniques && this.uniques.size() > 0){
            sb.deleteCharAt(sb.length()-1);
        }
        sb.append(")engine=innodb charset=utf8 comment='").append(this.getName()).append("';");
        return sb.toString();
    }

}