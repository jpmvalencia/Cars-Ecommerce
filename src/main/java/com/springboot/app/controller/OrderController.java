package com.springboot.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.springboot.app.model.Order;
import com.springboot.app.model.User;
import com.springboot.app.repository.UserRepository;
import com.springboot.app.service.OrderService;

@Controller
public class OrderController {

    // Inject the OrderService
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserRepository userRepository;

    // just for admin
    /** Get all the orders and show them */
    @GetMapping("/admin/orders/all")
    public String showOrders(@ModelAttribute Order order, Model model){

        // Get all the orders
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);

        // Add the order to the model to be shown in the form
        model.addAttribute("order", new Order());

        // Return the view
        return "orders";
    }

    /** Show all the orders filtering by user */
    @GetMapping("/app/user/orders")
    public String showOrdersByUser(@ModelAttribute Order order, Model model){
        // get the logged user
        // This class is used to get the user that is logged in
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByEmail(username).get();

        // Get all the orders by user
        List<Order> orders = orderService.getOrdersByUser(user);
        model.addAttribute("orders", orders);

        // Add the order to the model to be shown in the form
        model.addAttribute("order", new Order());

        // Return the view
        return "orders";
    }

}
