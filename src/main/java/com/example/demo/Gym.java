package com.example.demo;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Gym {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    // Validates that gymName is not null/blank and has a maximum length of 100 characters
    @NotBlank(message = "Gym name is mandatory")
    @Size(max = 100, message = "Gym name must be less than 100 characters")
    @Column(nullable = false, length = 100)
    private String gymName;
    
    // Validates that location is not null/blank and has a maximum length of 150 characters
    @NotBlank(message = "Location is mandatory")
    @Size(max = 150, message = "Location must be less than 150 characters")
    @Column(nullable = false, length = 150)
    private String location;
    
    // Validates that ownerName is not null/blank and has a maximum length of 100 characters
    @NotBlank(message = "Owner name is mandatory")
    @Size(max = 100, message = "Owner name must be less than 100 characters")
    @Column(nullable = false, length = 100)
    private String ownerName;
    
    // Validates that contactNumber is not null/blank and matches a 10-digit number pattern
    @NotBlank(message = "Contact number is mandatory")
    @Pattern(regexp = "\\d{10}", message = "Contact number must be 10 digits")
    @Column(nullable = false, length = 10)
    private String contactNumber;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGymName() {
        return gymName;
    }

    public void setGymName(String gymName) {
        this.gymName = gymName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
