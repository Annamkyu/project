package com.example.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/thymeleaf")
@Controller
public class ThymeleafExController {
    @GetMapping(value = "/ex")
    public String thymeleafExample(){
        return "thymeleafEx";
    }
}
