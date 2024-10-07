package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactsController {

    // Show the login page
    @GetMapping("/login")
    public String loginPage() {
        return "login";  // Returns the view named "login.html"
    }

    // Redirect to home after login
    @GetMapping("/home")
    public String homePage() {
        return "new";  // Returns the view named "home.html"
    }

    // Handle login POST request
    @PostMapping("/login")
    public String loginRedirect() {
        return "redirect:/home";  // Redirect to the "new" page after successful login
    }

    // Show the new page
    @GetMapping("/new")
    public String newPage() {
        return "new";  // Returns the view named "new.html"
    }
}
