package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ContactsController {

    private final List<User> users = new ArrayList<>();

    
    @GetMapping("/home")
    public String homePage() {
        return "home";  
    }

    @PostMapping("/login")
    public String loginRedirect() {
        return "new";  
    }

    @GetMapping("/add.html")
    public String addUserForm() {
        return "add-user";  
    }
    @GetMapping("/user/add")
    public String addUser() {
        return "add-user";  
    }


    @PostMapping("/submit-form")
    public String submitForm(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
       
        User newUser = new User(users.size() + 1, name, email, password);
        users.add(newUser);
        return "redirect:/users";  
    }

    @PostMapping("/user/add")
    public String submitForms(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
        User newUser = new User(users.size() + 1, name, email, password); 
        users.add(newUser);  
        return "redirect:/users";  
    }

    
    @GetMapping("/users")
    public String getUserList(Model model) {
        model.addAttribute("users", users);  
        return "user-list";  
    }

    @PostMapping("/user/edit")
    public String editUser(@RequestParam int id, @RequestParam String name, @RequestParam String email, @RequestParam String password) {
        
        User user = users.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
        if (user != null) {
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
        }
        return "redirect:/users";  
    }

    @GetMapping("/user/edit/{id}")
    public String editUserForm(@PathVariable int id, Model model) {
        User user = users.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
        if (user != null) {
            model.addAttribute("user", user);  
            return "edit-user";  
        }
        return "redirect:/users";  
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        users.removeIf(u -> u.getId() == id);  
        return "redirect:/users";  
    }
}