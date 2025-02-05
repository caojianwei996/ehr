package com.neusoft.ehr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 曹健伟
 * <p>
 * SpringBoot主入口
 */
@MapperScan("com.neusoft.ehr.mapper")
@SpringBootApplication
public class Application {
    /**
     * 主启动方法
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
