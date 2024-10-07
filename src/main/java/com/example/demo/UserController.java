package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Display form for adding a user
    @GetMapping("/add.html")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());  
        return "add-user";  
    }

    // Handle form submission to add or update a user
    @PostMapping("/add")
    public String addUserOrUpdate(@Valid @ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "add-user";
        }
        userService.saveUser(user);
        return "redirect:/user/list";  // Redirect to the user list upon successful addition/update
    }

    // Display the list of users
    @GetMapping("/list")
    public String listUsers(Model model) {
        List<User> users = userService.getAllUsers(); 
        model.addAttribute("users", users); 
        return "user-list";  // Show user list view
    }

    // Display form to edit a user
    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable int id, Model model) {
        User user = userService.getUserById(id);
        if (user == null) {
            return "redirect:/user/list";  // Redirect if no user found
        }
        model.addAttribute("user", user);
        return "edit-user";  // Show edit user form
    }

    // Handle form submission to edit a user
    @PostMapping("/edit")
    public String editUser(@RequestParam int id, @RequestParam String name, @RequestParam String email, @RequestParam String password) {
        userService.updateUser(id, name, email, passwordEncoder.encode(password));
        return "redirect:/user/list";  // Redirect to the user list after update
    }

    // Delete a user
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "redirect:/user/list";  // Redirect to user list after deletion
    }
}
