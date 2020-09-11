package com.cloud.easytoolsserver.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.cloud.easytoolsserver.code.CodeGennerator;
import com.cloud.easytoolsserver.model.GenneratorConfig;
import com.cloud.easytoolsserver.model.SourceConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/code")
public class GeneratorController {

    @Resource
    private CodeGennerator gennerator;

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("title", "欢迎使用");
        model.addAttribute("time", LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        return "index";
    }


    @PostMapping("/submit")
    public String submit(GlobalConfig globalConfig, StrategyConfig strategyConfig, DataSourceConfig dataSourceConfig, PackageConfig packageConfig, Model model) {
        try {
            gennerator.submit(new GenneratorConfig(globalConfig, strategyConfig, dataSourceConfig, packageConfig));
        } catch (Throwable e) {
            model.addAttribute("msg", e.getMessage());
            return "write";
        }
        model.addAttribute("msg", "任务已提交，请耐心等待...");
        return "write";
    }


    @ResponseBody
    @PostMapping("/loadTables")
    public List<Map<String, String>> tables(@RequestBody SourceConfig sourceConfig) {
        List<Map<String, String>> tableList = new ArrayList<>();
        DruidDataSource druid = null;
        DruidPooledConnection connection = null;
        try {
            druid = new DruidDataSource();
            druid.setUrl(sourceConfig.getUrl());
            druid.setUsername(sourceConfig.getUsername());
            druid.setPassword(sourceConfig.getPassword());
            connection = druid.getConnection();
            ResultSet tables = connection.getMetaData().getTables(connection.getCatalog(), connection.getSchema(), null, new String[]{"TABLE"});
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                Map<String, String> map = new HashMap<>();
                map.put("title", tableName);
                map.put("value", tableName);
                tableList.add(map);
            }
            tables.close();
            return tableList;
        } catch (Exception e) {
            try {
                if(connection != null){
                    connection.close();
                }
                if (druid != null) {
                    druid.close();
                }
            } catch (SQLException e1) {}
            return tableList;
        }
    }
}
