package com.snake.mysql.model;

import com.snake.mysql.ParseSQL;

import java.util.Iterator;
import java.util.Set;

/**
 * 唯一校验索引
 * @author wenxy
 * @version 1.00 ,Date: 2016-12-1 11:10:28
 */
public class Unique implements ParseSQL {

    private static final String symbol = ",";

    public String parseSQL() {
        StringBuffer sb = new StringBuffer();
        sb.append("unique ");
        if (null != this.getCode()) {
            sb.append(this.getCode());
        }
        sb.append("(");
        if (null != columns && columns.size() > 0) {
            Iterator<Column> iterator = columns.iterator();
            sb.append(iterator.next().getCode());
            while (iterator.hasNext()){
                sb.append(symbol);
                sb.append(iterator.next().getCode());
            }
        }
        sb.append("),");
        return sb.toString();
    }

    private Long id;
    private Long tableId;
    private String code;
    private String createdTime;
    private Long creatorId;
    private Set<Column> columns;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
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

    public Set<Column> getColumns() {
        return columns;
    }

    public void setColumns(Set<Column> columns) {
        this.columns = columns;
    }
}