package com.snake.mysql;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.io.IOException;
import java.io.InputStream;


public class MysqlFactory {

    public MysqlFactory() {
    }


    public static SqlSessionFactory buildSqlSessionFactory(String url, String username, String password) {

        return buildSqlSessionFactory("com.mysql.jdbc.Driver", url, username, password);

    }

    public static void addMapperXml(Configuration configuration, String resource) {
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            XMLMapperBuilder mapperParser = new XMLMapperBuilder(inputStream, configuration, resource, configuration.getSqlFragments());
            mapperParser.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory buildSqlSessionFactory(String driverClassName, String url, String username, String password) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setInitialSize(5);
        dataSource.setMinIdle(5);
        dataSource.setMaxActive(5);
        dataSource.setRemoveAbandoned(true);
        dataSource.setRemoveAbandonedTimeout(60);
        dataSource.setMaxWait(6000);
        dataSource.setDefaultAutoCommit(false);
        dataSource.setValidationQuery("select 1");
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxOpenPreparedStatements(10);
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        addMapperXml(configuration, "sqlmap/database/MysqlMapper.xml");
        return new SqlSessionFactoryBuilder().build(configuration);
    }

}
