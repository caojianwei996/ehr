import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * @author 曹健伟
 * <p>
 * MyBatis-Plus代码生成器
 */
public class Generator {
    /**
     * Main方法
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        // @formatter:off
        // 代码生成
        FastAutoGenerator
                // 数据库配置
                .create("jdbc:mysql://localhost:3306/ehr", "root", "root")
                // 全局配置
                .globalConfig(builder -> builder
                        // 作者
                        .author("曹健伟")
                        // 注释日期格式
                        .commentDate("yyyy-MM-dd")
                        // 实体类时间日期类型
                        .dateType(DateType.TIME_PACK)
                        // 不打开文件夹
                        .disableOpenDir()
                        // 启用SpringDoc
                        .enableSpringdoc()
                        // 输出路径
                        .outputDir("./generator/resultant")
                        // 结束配置
                        .build()
                )
                // 包配置
                .packageConfig(builder -> builder
                        // 父包
                        .parent("com.neusoft.ehr")
                        // 实体类包
                        .entity("entity.po")
                        // 结束配置
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
                        // 不构建Mapper
                        .mapperBuilder().disable()
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
