package com.shop.shoes.controller;

import com.shop.shoes.commom.CommomDataService;
import com.shop.shoes.entities.CartItem;
import com.shop.shoes.entities.Product;
import com.shop.shoes.service.ShoppingCartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;

@Controller
public class CartController extends CommomController{
    @Autowired
    HttpSession session;
    @Autowired
    CommomDataService commomDataService;
    @Autowired
    ShoppingCartService shoppingCartService;
    @GetMapping("/cart")
    public String shoppingCart(Model model){
        Collection<CartItem> cartItems = shoppingCartService.getCartItems();
        model.addAttribute("cartItems",cartItems);
        model.addAttribute("total",shoppingCartService.getAmount());
        double totalPrice = 0;
        for(CartItem cartItem: cartItems){
            double price = cartItem.getQuantity() * cartItem.getProduct().getPrice();
            totalPrice += price - (price*cartItem.getProduct().getDiscount()/100);
        }
        model.addAttribute("totalPrice",totalPrice);
        model.addAttribute("totalCartItems",shoppingCartService.getCount());
        return "web/cart";
    }
    // add cartItem
    @GetMapping("/addToCart")
    public String add(@RequestParam("productId")Long productId, HttpServletRequest request,Model model){
        Product product = productRepository.findById(productId).orElse(null);
        session = request.getSession();
        Collection<CartItem> cartItems = shoppingCartService.getCartItems();
        if(product != null){
            CartItem item = new CartItem();
            BeanUtils.copyProperties(product,item);
            item.setId(productId);
            item.setQuantity(1);
            item.setProduct(product);
            shoppingCartService.add(item);
        }
        session.setAttribute("cartItems",cartItems);
        model.addAttribute("totalCartItems", shoppingCartService.getCount());
        return "redirect:/products";
    }
    @SuppressWarnings("unlikely-arg-type")
    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id")Long id,HttpServletRequest request,Model model){
        Product product = productRepository.findById(id).orElse(null);
        Collection<CartItem> cartItems = shoppingCartService.getCartItems();
        session = request.getSession();
        if(product!=null){
            CartItem item = new CartItem();
            BeanUtils.copyProperties(product, item);
            item.setProduct(product);
            item.setId(id);
            shoppingCartService.remove(item);
        }
        model.addAttribute("totalCartItems", shoppingCartService.getCount());
        return "redirect:/cart";
    }
}
