package com.shop.shoes.controller;

import com.shop.shoes.commom.CommomDataService;
import com.shop.shoes.entities.CartItem;
import com.shop.shoes.entities.Order;
import com.shop.shoes.entities.User;
import com.shop.shoes.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class CheckoutController extends CommomController{
    @Autowired
    ShoppingCartService shoppingCartService;
    @Autowired
    CommomDataService commomDataService;
    @GetMapping("/checkout")
    public String checkout(Model model, User user){
        Order order = new Order();
        model.addAttribute("order",order);
        Collection<CartItem> cartItems = shoppingCartService.getCartItems();
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", shoppingCartService.getAmount());
        model.addAttribute("NoOfItems", shoppingCartService.getCount());
        double totalPrice = 0;
        for (CartItem cartItem : cartItems) {
            double price = cartItem.getQuantity() * cartItem.getProduct().getPrice();
            totalPrice += price - (price * cartItem.getProduct().getDiscount() / 100);
        }
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("totalCartItems", shoppingCartService.getCount());
        commomDataService.commonData(model,user);
        return "web/checkout";
    }
}
