package cn.jorian.generator;

/**
 * @author: jorian
 * @date: 2020/2/25 13:08
 * @description: this is  description for the class
 */

public interface GeneratorProps {

    /**
     * 项目子模块
     */
    static String GENE_MODULE = "x-generator.module";
    /**
     * 所在包
     */
    String GENE_PKG_MODULE = "x-generator.pkg.module";
    /**
     * 所在包的父包
     */
    String GENE_PKG_PARENT = "x-generator.pkg.parent";
    /**
     * 数据源驱动
     */
    String GENE_DS_DRIVER = "x-generator.ds.driver";
    /**
     * 数据源url
     */
    String GENE_DS_URL = "x-generator.ds.url";
    /**
     * 数据源账号
     */
    String GENE_DS_USERNAME = "x-generator.ds.username";
    /**
     * 数据源密码
     */
    String GENE_DS_PASSWORD = "x-generator.ds.password";
    /**
     * 全部表名，多个用逗号分隔
     */
    String GENE_TABLE_NAMES = "x-generator.table.names";
    /**
     * 表前缀，生成@TableName注解里的表前缀
     */
    String GENE_TABLE_PREFIX = "x-generator.table.prefix";
    /**
     * entity的父类
     */
    String GENE_BASE_ENTITY = "x-generator.base.entity";
    /**
     * entity父类的字段，多个用逗号分隔
     */
    String GENE_BASE_ENTITY_FIELDS = "x-generator.base.entity.fields";
    /**
     * controller的父类
     */
    String GENE_BASE_CONTROLLER = "x-generator.base.controller";
    /**
     * entity的类名
     */
    String GENE_ENTITY_NAME = "x-generator.entity.name";

    /**
     * 注释中的author
     */
    String GENE_AUTHOR = "x-generator.author";
}
