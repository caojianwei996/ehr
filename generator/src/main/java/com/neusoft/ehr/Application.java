package com.neusoft.ehr;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.neusoft.ehr.controller.BaseController;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collections;
import java.util.Scanner;

public class Application {
    private static final String basePath = "./generator/resultant";
    private static final String codePath = basePath + "/main/java";
    private static final String mapperPath = basePath + "/main/resources/mappers";

    public static void main(String[] args) {
        // @formatter:off
        Scanner scanner = new Scanner(System.in);
        System.out.println("Database name:");
        String url = "jdbc:mysql://localhost:3306/" + scanner.nextLine();
        System.out.println("Database username:");
        String username = scanner.nextLine();
        System.out.println("Database password:");
        String password = scanner.nextLine();
        System.out.println("Author:");
        String author = scanner.nextLine();
        String moduleName = "";
        FastAutoGenerator
                .create(url, username, password)
                .globalConfig(builder -> builder
                        .author(author)
                        .commentDate("yyyy-MM-dd")
                        .dateType(DateType.TIME_PACK)
                        .disableOpenDir()
                        .outputDir(codePath)
                        .build()
                )
                .packageConfig(builder -> builder
                        .parent("com.neusoft.ehr")
                        .moduleName(moduleName)
                        .entity("entity.po")
                        .mapper("repository")
                        .pathInfo(Collections.singletonMap(OutputFile.xml, mapperPath))
                        .build()
                )
                .strategyConfig(builder -> builder
                        .entityBuilder()
                        .convertFileName(entity -> entity + "Po")
                        .enableFileOverride()
                        .enableLombok()
                        .enableRemoveIsPrefix()
                        .enableTableFieldAnnotation()
                        .mapperBuilder()
                        .convertMapperFileName(entity -> entity + "Repository")
                        .convertXmlFileName(entity -> entity + "Repository")
                        .enableFileOverride()
                        .mapperAnnotation(Mapper.class)
                        .serviceBuilder()
                        .convertServiceFileName(entityName -> "I" + entityName + "Service")
                        .convertServiceImplFileName(entityName -> entityName + "Service")
                        .enableFileOverride()
                        .controllerBuilder()
                        .convertFileName(entity -> entity + "Controller")
                        .enableRestStyle()
                        .superClass(BaseController.class)
                        .build()
                )
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
        // @formatter:on
    }
}
