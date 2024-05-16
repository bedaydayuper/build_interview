package com.zhangpeng.buildinterview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan(basePackages = {"com.zhangpeng.buildinterview"})
@SpringBootApplication(scanBasePackages = {"com.zhangpeng.buildinterview"})
public class BuildInterviewApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuildInterviewApplication.class, args);
    }

}
