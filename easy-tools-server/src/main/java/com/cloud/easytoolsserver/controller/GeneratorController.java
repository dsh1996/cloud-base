package com.cloud.easytoolsserver.controller;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.cloud.easytoolsserver.code.CodeGennerator;
import com.cloud.easytoolsserver.model.GenneratorConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
    public List<String> tables(@RequestBody DataSourceConfig sourceConfig) {

        return new ArrayList<>();
    }
}
