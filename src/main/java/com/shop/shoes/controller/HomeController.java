package com.shop.shoes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController extends CommomController{
    @GetMapping("/")
    public String home(){
        return "web/index";
    }
}
