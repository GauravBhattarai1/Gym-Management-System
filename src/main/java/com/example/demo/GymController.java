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

    @GetMapping("/add")
    public String addGymForm(Model model) {
        model.addAttribute("gym", new Gym());  
        return "add-gym";  
    }


    @PostMapping("/add")
    public String submitGymForm(@Valid @ModelAttribute("gym") Gym gym, BindingResult result, Model model) {
        // Handle validation errors
        if (result.hasErrors()) {
            return "add-gym";  
        }

        gymService.saveGym(gym);
        return "redirect:/gym/list";  
    }

    @GetMapping("/list")
    public String getGymList(Model model) {
        List<Gym> gyms = gymService.getAllGyms(); 
        model.addAttribute("gyms", gyms);  
        return "gym-list";  
    }

    @GetMapping("/edit/{id}")
    public String editGymForm(@PathVariable int id, Model model) {
        Gym gym = gymService.getGymById(id);  
        if (gym != null) {
            model.addAttribute("gym", gym);  
            return "edit-gym";  
        }
        return "redirect:/gym/list";  
    }

    @PostMapping("/edit")
    public String editGym(@Valid @ModelAttribute("gym") Gym gym, BindingResult result) {
       
        if (result.hasErrors()) {
            return "edit-gym";  
        }

        gymService.saveGym(gym);
        return "redirect:/gym/list";  
    }

    @GetMapping("/delete/{id}")
    public String deleteGym(@PathVariable int id) {
        gymService.deleteGym(id);  
        return "redirect:/gym/list";  
    }
}
