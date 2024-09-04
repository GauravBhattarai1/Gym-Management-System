package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactsController {

    @GetMapping("/home")
    public String home() {
        return "home"; 
    }

    @PostMapping("/submit-form")
    public String submitForm(@RequestParam String name, @RequestParam String email, @RequestParam String message) {
       
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Message: " + message);

       
        return "result"; 
    }
}