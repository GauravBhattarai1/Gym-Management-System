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

    // Display form for adding a new user
    @GetMapping("/add.html")
    public String addUserForm1(Model model) {
        model.addAttribute("user", new User());  // Passing an empty user object to the form
        return "add-user";  // Renders add-user.html from the templates folder
    }

    @GetMapping("/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());  // Passing an empty user object to the form
        return "add-user";  // Renders add-user.html from the templates folder
    }

    // Handle form submission for adding a new user with file upload
    @Autowired
    private PasswordEncoder passwordEncoder;  // Inject the PasswordEncoder

    @PostMapping("/adduser")
    public String submitForm(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        // Handle validation errors
        if (result.hasErrors()) {
            return "add-user";  // Return to the form if validation fails
        }

        // Encrypt the user's password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save the user using the service
        userService.saveUser(user);
        return "redirect:/user/list";  // Redirect to the user list view
    }
    // Display the list of users
    @GetMapping("/list")
    public String getUserList(Model model) {
        List<User> users = userService.getAllUsers();  // Fetch all users from the service
        model.addAttribute("users", users);  // Add users list to the model
        return "user-list";  // Renders user-list.html
    }

    // Display form for editing an existing user
    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable int id, Model model) {
        User user = userService.getUserById(id);  // Find the user by ID via the service
        if (user != null) {
            model.addAttribute("user", user);  // Pass the user object to the edit form
            return "edit-user";  // Renders edit-user.html
        }
        return "redirect:/user/list";  // Redirect to user list if user is not found
    }

    // Handle form submission for editing an existing user
    @PostMapping("/edit")
    public String editUser(@RequestParam int id, @RequestParam String name, @RequestParam String email, @RequestParam String password) {
        // Find the user by ID and update their details via the service
        userService.updateUser(id, name, email, password);
        return "redirect:/user/list";  // Redirect to the user list after editing
    }

    // Delete a user by ID
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);  // Delete user via the service
        return "redirect:/user/list";  // Redirect to the user list after deletion
    }
}
