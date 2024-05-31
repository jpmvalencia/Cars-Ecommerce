package com.springboot.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.springboot.app.model.User;
import com.springboot.app.service.JpaUserDetailsService;


@Controller
public class AdminUserController {

    @Autowired
    private JpaUserDetailsService userDetailsService;

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";   
    }

    @GetMapping("/admin/user-list")
    public String userList(Model model) {
        List<User> users = userDetailsService.getAllUsers();
        model.addAttribute("users", users);
        return "user-list";   
    }
}
