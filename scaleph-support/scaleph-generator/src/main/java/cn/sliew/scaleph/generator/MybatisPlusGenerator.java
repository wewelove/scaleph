package cn.sliew.scaleph.generator;

import cn.sliew.scaleph.dao.entity.BaseDO;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MybatisPlusGenerator {

    private final static String AUTHOR = "wangqi";
    private final static String URL = "jdbc:mysql://127.0.0.1:3306/scaleph?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "123456";
    private final static String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String BASE_PACKAGE = "cn.sliew";
    private static final String MODULE = "scaleph";
    private static final String TABLE_PREFIX = "t_";

    /**
     * just add table names here and run the {@link #main(String[])} method.
     */
    private static final String[] TABLES = {"meta_datasource2"};

    public static void main(String[] args) {
        //自动生成配置
        AutoGenerator generator = new AutoGenerator();
        generator.setGlobalConfig(globalConfig());
        generator.setDataSource(dataSourceConfig());
        generator.setPackageInfo(packageConfig());
        generator.setStrategy(strategyConfig());
        generator.setCfg(injectionConfig());
        generator.execute();
    }

    /**
     * 数据源配置
     *
     * @return DataSourceConfig
     */
    private static DataSourceConfig dataSourceConfig() {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setUrl(URL);
        dsc.setUsername(USERNAME);
        dsc.setPassword(PASSWORD);
        dsc.setDriverName(DRIVER_NAME);
        return dsc;
    }

    /**
     * 全局配置
     *
     * @return GlobalConfig
     */
    private static GlobalConfig globalConfig() {
        GlobalConfig config = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        config.setOutputDir(projectPath + "/scaleph-support/scaleph-generator/src/main/java/");
        config.setAuthor(AUTHOR);
        config.setOpen(false);
        config.setFileOverride(true);
        config.setBaseResultMap(true);
        config.setDateType(DateType.ONLY_DATE);
        config.setBaseColumnList(false);
        config.setIdType(IdType.AUTO);
        config.setMapperName("%sMapper");
        config.setXmlName("%sMapper");
        config.setEntityName("%s");
        config.setServiceName("%sService");
        config.setServiceImplName("%sServiceImpl");
        config.setControllerName("%sController");
        config.setSwagger2(true);
        return config;
    }

    /**
     * 包配置
     *
     * @return PackageConfig
     */
    private static PackageConfig packageConfig() {
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(MODULE);
        pc.setParent(BASE_PACKAGE);
        pc.setXml("dao.mapper");
        pc.setMapper("dao.mapper");
        pc.setEntity("dao.entity");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setController("api.controller");
        return pc;
    }

    /**
     * 策略配置
     *
     * @return StrategyConfig
     */
    private static StrategyConfig strategyConfig() {
        StrategyConfig strategy = new StrategyConfig();
        strategy.setTablePrefix(TABLE_PREFIX);
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass(BaseDO.class);
        strategy.setEntityLombokModel(true);
        strategy.setSuperEntityColumns(new String[]{"id", "creator", "create_time", "editor", "update_time"});
        strategy.setInclude(TABLES);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setRestControllerStyle(true);
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);
        return strategy;
    }

    /**
     * 自定义配置
     *
     * @return InjectionConfig
     */
    private static InjectionConfig injectionConfig() {
        InjectionConfig ic = new InjectionConfig() {
            @Override
            public void initMap() {

            }
        };

        return ic;
    }


}