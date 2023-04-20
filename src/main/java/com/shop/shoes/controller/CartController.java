package com.shop.shoes.controller;

import com.shop.shoes.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {
    @GetMapping(value = "/checkout")
    public String checkout(Model model, User user){
        return "web/shoppingCart_chekout";
    }
}
