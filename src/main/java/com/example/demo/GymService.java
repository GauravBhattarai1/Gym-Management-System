package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GymService {

    @Autowired
    private GymRepository gymRepository;

    public void saveGym(Gym gym) {
        gymRepository.save(gym);  // Save the gym object to the database
    }

    public Gym getGymById(int id) {
        return gymRepository.findById(id).orElse(null);  // Return the gym or null if not found
    }

    public void deleteGym(int id) {
        gymRepository.deleteById(id);  // Delete the gym by its ID
    }

    public List<Gym> getAllGyms() {
        return gymRepository.findAll();  // Fetch all gyms from the repository
    }
}
