package com.web.${app.code};

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.web.${app.code}.mapper")
@SpringBootApplication
public class SpringWebApplication {

    public static void main(String[] args){
        SpringApplication.run(SpringWebApplication.class);
    }

}
