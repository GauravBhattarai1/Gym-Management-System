package com.example.demo;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Gym {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotBlank(message = "Gym name is mandatory")
    @Size(max = 100, message = "Gym name must be less than 100 characters")
    @Column(nullable = false, length = 100)
    private String gymName;
    
    @NotBlank(message = "Location is mandatory")
    @Size(max = 150, message = "Location must be less than 150 characters")
    @Column(nullable = false, length = 150)
    private String location;
    
    @NotBlank(message = "Owner name is mandatory")
    @Size(max = 100, message = "Owner name must be less than 100 characters")
    @Column(nullable = false, length = 100)
    private String ownerName;
    
    @NotBlank(message = "Contact number is mandatory")
    @Pattern(regexp = "\\d{10}", message = "Contact number must be 10 digits")
    @Column(nullable = false, length = 10)
    private String contactNumber;

    @NotBlank(message = "Description is mandatory")
    @Size(max = 255, message = "Description must be less than 255 characters")
    @Column(nullable = false, length = 255)
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Column(name = "image_url")
    private String imageUrl;  // Field to store the image URL

    // Getters and setters for all fields including imageUrl
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
