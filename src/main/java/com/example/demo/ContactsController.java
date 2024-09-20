package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactsController {

    // Home Page Mapping
    @GetMapping("/home")
    public String homePage() {
        return "home";  // Returns home.html
    }

    // Login functionality
    @PostMapping("/login")
    public String loginRedirect() {
        return "new";  // Redirects after login, returning new.html
    }
}
