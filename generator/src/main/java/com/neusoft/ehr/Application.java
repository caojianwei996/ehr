package com.neusoft.ehr;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;
import java.util.Scanner;

/**
 * @author 曹健伟
 * <p>
 * MyBatis-Plus代码生成器
 */
public class Application {
    /**
     * 数据库URL
     */
    private static final String url = "jdbc:mysql://localhost:3306/ehr";
    /**
     * 数据库用户名
     */
    private static final String username = "root";
    /**
     * 数据库密码
     */
    private static final String password = "root";
    /**
     * 输出路径
     */
    private static final String basePath = "./generator/resultant";

    /**
     * Main方法
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        // @formatter:off
        // 准备读取键盘输入
        Scanner scanner = new Scanner(System.in);
        // 输入作者
        System.out.println("Author:");
        String author = scanner.nextLine();
        // 代码生成
        FastAutoGenerator
                // 数据库配置
                .create(url, username, password)
                // 全局配置
                .globalConfig(builder -> builder
                        // 作者
                        .author(author)
                        // 注释日期格式
                        .commentDate("yyyy-MM-dd")
                        // 实体类时间日期类型
                        .dateType(DateType.TIME_PACK)
                        // 不打开文件夹
                        .disableOpenDir()
                        // 启用SpringDoc
                        .enableSpringdoc()
                        // 输出路径
                        .outputDir(basePath + "/main/java")
                        // 结束配置
                        .build()
                )
                // 包配置
                .packageConfig(builder -> builder
                        // 父包
                        .parent("com.neusoft.ehr")
                        // 实体类包
                        .entity("entity.po")
                        // Mapper包
                        .mapper("mapper")
                        // 特殊路径信息
                        .pathInfo(Collections.singletonMap(OutputFile.xml, basePath + "/main/resources/mappers"))
                        //结束配置
                        .build()
                )
                // 策略配置
                .strategyConfig(builder -> builder
                        // 实体构建
                        .entityBuilder()
                        // 格式化实体类名称
                        .convertFileName(entity -> entity + "Po")
                        // 允许文件覆盖
                        .enableFileOverride()
                        // 使用Lombok
                        .enableLombok()
                        // 格式化Boolean变量前缀
                        .enableRemoveIsPrefix()
                        // 启用表字段注解
                        .enableTableFieldAnnotation()
                        // 设置逻辑删除表字段
                        .logicDeleteColumnName("is_deleted")
                        // 设置逻辑删除类字段
                        .logicDeletePropertyName("deleted")
                        // 设置乐观锁表字段
                        .versionColumnName("version")
                        // 设置乐观锁类字段
                        .versionPropertyName("version")
                        // Mapper构建
                        .mapperBuilder()
                        // 格式化Mapper名称
                        .convertMapperFileName(entity -> entity + "Mapper")
                        // 格式化XML名称
                        .convertXmlFileName(entity -> entity + "Mapper")
                        // 允许文件覆盖
                        .enableFileOverride()
                        // 不构建Service
                        .serviceBuilder().disable()
                        // 不构建Controller
                        .controllerBuilder().disable()
                        // 结束配置
                        .build()
                )
                // 模板引擎
                .templateEngine(
                        new FreemarkerTemplateEngine()
                )
                // 执行
                .execute();
        // @formatter:on
    }
}
