package com.cloud.easytoolsserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/code")
public class GeneratorController {

    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("title", "欢迎使用");
        model.addAttribute("time", LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        return  "index";
    }



}
