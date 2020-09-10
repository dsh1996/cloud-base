package com.cloud.easytoolsserver.code;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.cloud.easytoolsserver.base.SuperController;
import com.cloud.easytoolsserver.model.GenneratorConfig;

import java.sql.Driver;

public class CodeGennerator {

    static String DRIVER_NAME = Driver.class.getName();
    static String URL = "jdbc:mysql://192.168.68.128:10000/auth?serverTimezone=UTC";

    public static void main(String[] args) {

        new CodeGennerator().run();
    }

    public Boolean submit(GenneratorConfig config) {
        AutoGenerator generator = new AutoGenerator();
        GlobalConfig globalConfig = config.getGlobalConfig();
        globalConfig.setOutputDir("G://code")   //设置输出路径
                .setFileOverride(true)  //设置文件覆盖
                .setServiceName("%sService");

        StrategyConfig strategyConfig = config.getStrategyConfig();
        strategyConfig.setCapitalMode(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel);
        generator.setGlobalConfig(globalConfig)
                .setDataSource(config.getDataSourceConfig())
                .setStrategy(strategyConfig)
                .setPackageInfo(config.getPackageConfig());
        generator.execute();
        return true;
    }

    public void run() {
        AutoGenerator generator = new AutoGenerator();

        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setAuthor("dengsh")
                .setOutputDir("G://code")   //设置输出路径
                .setFileOverride(true)  //设置文件覆盖
                .setServiceName("%sService")
                .setIdType(IdType.ASSIGN_ID) // 设置主键生成策略
                .setSwagger2(true)
                .setBaseResultMap(true) //基本结果集合
                .setBaseColumnList(true);   //设置基本的列

        //数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setDriverName(DRIVER_NAME)
                .setUrl(URL)
                .setUsername("root")
                .setPassword("root");

        //策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setEntityLombokModel(true)
                .setEntityTableFieldAnnotationEnable(true)
                .setSuperControllerClass(SuperController.class)
                .setTablePrefix("tab_")
                .setInclude("tab_user");


        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.cloud.server")
                .setMapper("mapper")
                .setService("service")
                .setController("controller")
                .setEntity("model")
                .setModuleName("dto")
                .setXml("mapper");

        generator.setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig);

        generator.execute();
    }
}
