package com.yys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yys.web.mapper")
public class YunPictureApplication {

    public static void main(String[] args) {
        SpringApplication.run(YunPictureApplication.class, args);
    }

}
