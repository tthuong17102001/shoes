package com.shop.shoes.controller;

import com.shop.shoes.commom.CommomDataService;
import com.shop.shoes.entities.User;
import com.shop.shoes.repository.FavoriteRepository;
import com.shop.shoes.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController extends CommomController{
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CommomDataService commomDataService;
    @Autowired
    FavoriteRepository favoriteRepository;
    @GetMapping("/")
    public String home(Model model, User user){
        commomDataService.commonData(model,user);
        return "web/index";
    }
}
