package com.springboot.app.controller;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.springboot.app.model.User;
import com.springboot.app.service.JpaUserDetailsService;

import org.springframework.ui.Model;

@Controller
public class UserController {

    private final JpaUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public UserController(JpaUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }
    
    @GetMapping("/user/signup")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/user/signup/submit")
    public String saveNewUser(@ModelAttribute User user, Model model) {
        try{
            UserDetails existingUser = userDetailsService.loadUserByUsername(user.getEmail());
            model.addAttribute("error", "User already exists " + existingUser.getUsername());
            return "signup";
        }
        
        catch(UsernameNotFoundException e){}
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        userDetailsService.save(user);
        
        return "redirect:/user/login";
    }

    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

}