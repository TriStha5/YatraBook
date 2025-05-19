package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class mainController {

    @Autowired
    private UserRepository uRepository;

    @GetMapping("/")
    public String landing(){
        return "index";
    }

    @GetMapping("/signUpPage")
    public String signupPage() {
        return "signup";
    }
    
    @PostMapping("/signup")
    public String signup(@ModelAttribute User u) {
        uRepository.save(u);
        return "login";
    }
    

    @GetMapping("/loginPage")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute User u, Model model, HttpSession session) {
        if (uRepository.existsByEmailAndPassword(u.getEmail(), u.getPassword())) {
            session.setAttribute("activeUser", u.getName());
            session.setMaxInactiveInterval(5);

            return "userHome";
        }
        else{
            return "login";
        }
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }
    
}
