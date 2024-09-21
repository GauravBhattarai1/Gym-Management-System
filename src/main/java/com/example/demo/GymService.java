package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GymService {

    @Autowired
    private GymRepository gymRepository;

    // Retrieve all gyms
    public List<Gym> getAllGyms() {
        return gymRepository.findAll();
    }

    // Save or update a gym
    public void saveGym(Gym gym) {
        gymRepository.save(gym); // Save the gym object to the database
    }

    // Find a gym by ID
    public Gym getGymById(int id) {
        Optional<Gym> gym = gymRepository.findById(id);
        return gym.orElse(null); // Return the gym or null if not found
    }

    // Delete a gym by ID
    public void deleteGym(int id) {
        gymRepository.deleteById(id); // Delete the gym by its ID
    }
}
