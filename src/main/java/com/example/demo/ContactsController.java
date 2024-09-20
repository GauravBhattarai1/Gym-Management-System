package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactsController {

    @GetMapping("/home")
    public String homePage() {
        return "home";  
    }

    // Login functionality
    @PostMapping("/login")
    public String loginRedirect() {
        return "new";  
    }
}
