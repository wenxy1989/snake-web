package com.snake.mysql;

import com.snake.mysql.model.Column;
import com.snake.mysql.model.Table;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DatabaseService {

    private SqlSessionFactory sqlSessionFactory;

    public void setProperties(Map<String, String> map) {
        this.sqlSessionFactory = new MysqlFactory().buildSqlSessionFactory(map.get("url"), map.get("username"), map.get("password"));
    }

    public List<Table> selectTableList(Map map) {
        SqlSession session = this.sqlSessionFactory.openSession();
        try {
            return session.selectList("com.snake.mysql.model.Database.selectTableList", map);
        } finally {
            session.close();
        }
    }

    public List<Column> selectColumnList(Map map) {
        SqlSession session = this.sqlSessionFactory.openSession();
        try {
            return session.selectList("com.snake.mysql.model.Database.selectColumnList", map);
        } finally {
            session.close();
        }
    }
}
