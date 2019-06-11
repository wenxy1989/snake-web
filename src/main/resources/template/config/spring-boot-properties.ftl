server.port=8080

spring.datasource.username=root
spring.datasource.password=Root_123456
spring.datasource.url=jdbc:mysql://localhost:3306/glucose_db?useUnicode=true&characterEncoding=utf8&useSSL=true&serverTimezone=UTC
spring.datasource.driver-class-name=com.mysql.jdbc.Driver

mybatis.mapper-locations=classpath:mapping/*Mapper.xml
mybatis.type-aliases-package=com.web.${application.code}.entity

logging.level.com.web.${application.code}.mapper=debug