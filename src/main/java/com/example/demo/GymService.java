package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GymService {

    @Autowired
    private GymRepository gymRepository;

    public List<Gym> getAllGyms() {
        return gymRepository.findAll();
    }

    public void saveGym(Gym gym) {
        gymRepository.save(gym); 
    }

    public Gym getGymById(int id) {
        Optional<Gym> gym = gymRepository.findById(id);
        return gym.orElse(null); 
    }

    public void deleteGym(int id) {
        gymRepository.deleteById(id); 
    }
}
