package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/gym")
public class GymController {

    @Autowired
    private GymService gymService;

    // Display form for adding a new gym
  
    @GetMapping("/add")
    public String addGymForm(Model model) {
        model.addAttribute("gym", new Gym());  // Make sure "gym" is being added
        return "add-gym";  // This should render add-gym.html
    }


    // Handle form submission for adding a new gym
    @PostMapping("/add")
    public String submitGymForm(@Valid @ModelAttribute("gym") Gym gym, BindingResult result, Model model) {
        // Handle validation errors
        if (result.hasErrors()) {
            return "add-gym";  // Return to the form if validation fails
        }

        // Save the gym using the service
        gymService.saveGym(gym);
        return "redirect:/gym/list";  // Redirect to the gym list view
    }

    // Display the list of gyms
    @GetMapping("/list")
    public String getGymList(Model model) {
        List<Gym> gyms = gymService.getAllGyms();  // Fetch all gyms from the service
        model.addAttribute("gyms", gyms);  // Add gyms list to the model
        return "gym-list";  // Renders gym-list.html
    }

    // Display form for editing an existing gym
    @GetMapping("/edit/{id}")
    public String editGymForm(@PathVariable int id, Model model) {
        Gym gym = gymService.getGymById(id);  // Find the gym by ID via the service
        if (gym != null) {
            model.addAttribute("gym", gym);  // Pass the gym object to the edit form
            return "edit-gym";  // Renders edit-gym.html
        }
        return "redirect:/gym/list";  // Redirect to gym list if gym is not found
    }

    // Handle form submission for editing an existing gym
    @PostMapping("/edit")
    public String editGym(@Valid @ModelAttribute("gym") Gym gym, BindingResult result) {
        // Handle validation errors
        if (result.hasErrors()) {
            return "edit-gym";  // Return to the edit form if validation fails
        }

        // Save the updated gym using the service
        gymService.saveGym(gym);
        return "redirect:/gym/list";  // Redirect to the gym list after editing
    }

    // Delete a gym by ID
    @GetMapping("/delete/{id}")
    public String deleteGym(@PathVariable int id) {
        gymService.deleteGym(id);  // Delete gym via the service
        return "redirect:/gym/list";  // Redirect to the gym list after deletion
    }
}
