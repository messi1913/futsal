package com.sangmessi.futsal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;

@Controller
public class JspController {

    @RequestMapping("/hello")
    public String index(Model model) {
        model.addAttribute("name", "Spring Boot from milly");
        return "hello";
    }

    @RequestMapping("/hello2")
    public String index2() {
        //model.addAttribute("name", "Spring Boot from sangmessi");
        return "futsal";
    }


}
