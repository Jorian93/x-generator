package cn.jorian.generator;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author: jorian
 * @date: 2020/2/25 13:08
 * @description: this is  description for the class
 */
public class GeneratorExecutor {
    private GeneratorConfig config;

    public GeneratorExecutor(GeneratorConfig config) {
        this.config = mergeProps(config);
    }

    private GeneratorConfig mergeProps(GeneratorConfig config) {
        if (null == config) {
            config = new GeneratorConfig();
        }
        Properties props = new Properties();
        try {
            props.load(GeneratorExecutor.class.getClassLoader().getResourceAsStream("generator.properties"));
        } catch (IOException e) {
            System.err.println("自动代码生成器读取配置文件参数失败...");
        }

        if (StringUtils.isBlank(config.getGeneModule())) {
            config.setGeneModule(props.getProperty(GeneratorProps.GENE_MODULE));
        }
        if (StringUtils.isBlank(config.getGenePkgModule())) {
            config.setGenePkgModule(props.getProperty(GeneratorProps.GENE_PKG_MODULE));
        }
        if (StringUtils.isBlank(config.getGenePkgParent())) {
            config.setGenePkgParent(props.getProperty(GeneratorProps.GENE_PKG_PARENT));
        }
        if (StringUtils.isBlank(config.getGeneDsDriver())) {
            config.setGeneDsDriver(props.getProperty(GeneratorProps.GENE_DS_DRIVER));
        }
        if (StringUtils.isBlank(config.getGeneDsUrl())) {
            config.setGeneDsUrl(props.getProperty(GeneratorProps.GENE_DS_URL));
        }
        if (StringUtils.isBlank(config.getGeneDsUsername())) {
            config.setGeneDsUsername(props.getProperty(GeneratorProps.GENE_DS_USERNAME));
        }
        if (StringUtils.isBlank(config.getGeneDsPassword())) {
            config.setGeneDsPassword(props.getProperty(GeneratorProps.GENE_DS_PASSWORD));
        }

        if (StringUtils.isBlank(config.getGeneTableNames())) {
            config.setGeneTableNames(props.getProperty(GeneratorProps.GENE_TABLE_NAMES));
        }
        if (StringUtils.isBlank(config.getGeneTablePrefix())) {
            config.setGeneTablePrefix(props.getProperty(GeneratorProps.GENE_TABLE_PREFIX));
        }

        if (StringUtils.isBlank(config.getGeneBaseEntity())) {
            config.setGeneBaseEntity(props.getProperty(GeneratorProps.GENE_BASE_ENTITY));
        }
        if (StringUtils.isBlank(config.getGeneBaseEntityFields())) {
            config.setGeneBaseEntityFields(props.getProperty(GeneratorProps.GENE_BASE_ENTITY_FIELDS));
        }
        if (StringUtils.isBlank(config.getGeneBaseController())) {
            config.setGeneBaseController(props.getProperty(GeneratorProps.GENE_BASE_CONTROLLER));
        }


        if (StringUtils.isBlank(config.getGeneEntityName())) {
            config.setGeneEntityName(props.getProperty(GeneratorProps.GENE_ENTITY_NAME));
        }
        if (StringUtils.isBlank(config.getGeneAuthor())) {
            config.setGeneAuthor(props.getProperty(GeneratorProps.GENE_AUTHOR));
        }
        return config;
    }


    public void execute() {

        // 代码生成器
        AutoGenerator generator = new AutoGenerator();
        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();

        String projectPath = System.getProperty("user.dir");
       /* globalConfig.setEntityName(config.getGeneEntityName());*/
        globalConfig.setOutputDir(config.getGeneModule()==null?"":config.getGeneModule()+projectPath+"/src/main/java");
        globalConfig.setAuthor(config.getGeneAuthor());
        globalConfig.setOpen(false);
        generator.setGlobalConfig(globalConfig);

        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(config.getGeneDsUrl());
        dataSourceConfig.setSchemaName("public");
        dataSourceConfig.setDriverName(config.getGeneDsDriver());
        dataSourceConfig.setUsername(config.getGeneDsUsername());
        dataSourceConfig.setPassword(config.getGeneDsPassword());
        generator.setDataSource(dataSourceConfig);

        // 包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setModuleName(config.getGenePkgModule());
        packageConfig.setParent(config.getGenePkgParent());
        generator.setPackageInfo(packageConfig);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();
        templateConfig.setXml(null);
        generator.setTemplate(templateConfig);

        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {

            }
        };

        // 如果模板引擎是 freemarker
        //String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
         String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath
                        +"/src/main/resources/mapper/"
                        + packageConfig.getModuleName()
                        + "/" + tableInfo.getEntityName()
                        + "Mapper" + StringPool.DOT_XML;
            }
        });
        injectionConfig.setFileOutConfigList(focList);
        generator.setCfg(injectionConfig);


        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);

        if (StringUtils.isNotBlank(config.getGeneBaseEntity())) {
            strategy.setSuperEntityClass(config.getGeneBaseEntity());
        }
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);

        if (StringUtils.isNotBlank(config.getGeneBaseController())) {
            strategy.setSuperControllerClass(config.getGeneBaseController());
        }
        strategy.setInclude(config.getGeneTableNames().split(","));
        String superFields = config.getGeneBaseEntityFields();
        if(StringUtils.isNotBlank(superFields)) {
            strategy.setSuperEntityColumns(superFields.split(","));
        }
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(config.getGeneTablePrefix());
        generator.setStrategy(strategy);
        //设置模板引擎
        //generator.setTemplateEngine(new FreemarkerTemplateEngine());
        generator.execute();
    }
}
