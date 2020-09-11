package com.cloud.easytoolsserver.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.alibaba.druid.proxy.DruidDriver;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.cloud.easytoolsserver.code.CodeGennerator;
import com.cloud.easytoolsserver.model.GenneratorConfig;
import com.cloud.easytoolsserver.model.SourceConfig;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.sql.Driver;
import java.sql.ResultSet;
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


    @ResponseBody
    @PostMapping("/submit")
    public String submit(@RequestBody GenneratorConfig config) {
        try {
            gennerator.submit(config);
        } catch (Throwable e) {
            return "Field";
        }
        return "SUCCESS";
    }


    @ResponseBody
    @PostMapping("/loadTables")
    public List<Map<String, String>> tables(@RequestBody SourceConfig sourceConfig) {
        List<Map<String, String>> tableList = new ArrayList<>();
        try {
            DruidDataSource druid = new DruidDataSource();
            druid.setUrl(sourceConfig.getUrl());
            druid.setUsername(sourceConfig.getUsername());
            druid.setPassword(sourceConfig.getPassword());
            DruidPooledConnection connection = druid.getConnection();
            ResultSet tables = connection.getMetaData().getTables(connection.getCatalog(), connection.getSchema(), null, new String[]{"TABLE"});
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                Map<String, String> map = new HashMap<>();
                map.put("title", tableName);
                map.put("value", tableName);
                tableList.add(map);
            }
            return tableList;
        } catch (Exception e) {
            return tableList;
        }
    }
}
