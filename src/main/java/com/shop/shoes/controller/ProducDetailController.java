package com.shop.shoes.controller;

import com.shop.shoes.commom.CommomDataService;
import com.shop.shoes.entities.Product;
import com.shop.shoes.entities.User;
import com.shop.shoes.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProducDetailController extends CommomController{
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CommomDataService commomDataService;
    @GetMapping("/detail")
    public String productDetail(@RequestParam("id")Long id, Model model, User user){
        Product product = productRepository.findById(id).orElse(null);
        model.addAttribute("product",product);
        commomDataService.commonData(model,user);
        return "web/detail";
    }
}
