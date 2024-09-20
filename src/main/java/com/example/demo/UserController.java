package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.Valid;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/add.html")
    public String addUserForm1(Model model) {
        model.addAttribute("user", new User());  
        return "add-user";  
    }

    @GetMapping("/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());  
        return "add-user"; 
    }

    @Autowired
    private PasswordEncoder passwordEncoder; 

    @PostMapping("/adduser")
    public String submitForm(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        
        if (result.hasErrors()) {
            return "add-user";  
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userService.saveUser(user);
        return "redirect:/user/list";  
    }
    
    @GetMapping("/list")
    public String getUserList(Model model) {
        List<User> users = userService.getAllUsers(); 
        model.addAttribute("users", users); 
        return "user-list";  
    }

    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable int id, Model model) {
        User user = userService.getUserById(id);  
        if (user != null) {
            model.addAttribute("user", user);  
            return "edit-user"; 
        }
        return "redirect:/user/list"; 
    }

    @PostMapping("/edit")
    public String editUser(@RequestParam int id, @RequestParam String name, @RequestParam String email, @RequestParam String password) {
      
        userService.updateUser(id, name, email, password);
        return "redirect:/user/list";  
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);  
        return "redirect:/user/list";  
    }
}
