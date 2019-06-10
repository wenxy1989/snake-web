package com.snake.mysql.model;

import java.util.List;

public class Database {

    private String name;
    private String username;
    private String password;
    private String database;
    private String url;

    private List<Table> tableList;

    public Database(){}

    public Database(String name, String username, String password, String url, String database) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.url = url;
        this.database = database;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Table> getTableList() {
        return tableList;
    }

    public void setTableList(List<Table> tableList) {
        this.tableList = tableList;
    }
}
