package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.util.Base64Utils;
import java.io.ByteArrayOutputStream;
import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/gym")
public class GymController {

    @Autowired
    private GymService gymService;

    // Show the "Add Gym" form
    @GetMapping("/add")
    public String addGymForm(Model model) {
        model.addAttribute("gym", new Gym());
        return "add-gym";  // Renders the 'add-gym' form
    }

    // Handle form submission for adding a gym
    @PostMapping("/add")
    public String submitGymForm(@Valid @ModelAttribute("gym") Gym gym, BindingResult result) {
        if (result.hasErrors()) {
            return "add-gym";  // If there are validation errors, reload the form
        }
        gymService.saveGym(gym);  // Save the gym details
        return "redirect:/gym/list";  // Redirect to the gym list after successful submission
    }

    // Display the list of all gyms
    @GetMapping("/list")
    public String getGymList(Model model) {
        List<Gym> gyms = gymService.getAllGyms();
        model.addAttribute("gyms", gyms);
        return "gym-list";  // Render the gym list template
    }

    // Display gym selection page
    @GetMapping("/select-gym")
    public String selectGym(Model model) {
        model.addAttribute("gyms", gymService.getAllGyms());
        return "select-gym";
    }

    // Handle the booking for a specific gym
    @PostMapping("/book-gym")
    public String bookGym(@RequestParam("gymId") int gymId,
                          @RequestParam("selectedDate") String selectedDate,
                          @RequestParam("selectedTime") String selectedTime,
                          Model model) throws Exception {

        Gym gym = gymService.getGymById(gymId);
        if (gym == null) {
            model.addAttribute("error", "Gym not found");
            return "error";
        }

        // Generate QR code
        String qrCodeImage = generateQRCodeImage("Booking for " + gym.getGymName() +
                                                 " on " + selectedDate + " at " + selectedTime);
        model.addAttribute("gym", gym);
        model.addAttribute("selectedDate", selectedDate);
        model.addAttribute("selectedTime", selectedTime);
        model.addAttribute("qrCodeImage", qrCodeImage);

        return "booking-confirmation";
    }

    private String generateQRCodeImage(String barcodeText) throws Exception {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = 
            barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
        byte[] qrImage = outputStream.toByteArray();
        return Base64Utils.encodeToString(qrImage);
    }



    // Handle the "Edit Gym" form
    @GetMapping("/edit/{id}")
    public String editGymForm(@PathVariable("id") int id, Model model) {
        Gym gym = gymService.getGymById(id);
        if (gym == null) {
            model.addAttribute("error", "Gym not found");
            return "error";
        }
        model.addAttribute("gym", gym);
        return "edit-gym";  // Render the 'edit-gym' form
    }

    // Handle form submission for editing a gym
    @PostMapping("/edit/{id}")
    public String submitEditGymForm(@PathVariable("id") int id, @Valid @ModelAttribute("gym") Gym gym, BindingResult result) {
        if (result.hasErrors()) {
            return "edit-gym";  // If there are validation errors, reload the form
        }
        gym.setId(id);  // Ensure the gym's ID remains the same
        gymService.saveGym(gym);  // Save the updated gym details
        return "redirect:/gym/list";  // Redirect to the gym list after successful edit
    }

    // Handle gym deletion
    @GetMapping("/delete/{id}")
    public String deleteGym(@PathVariable("id") int id) {
        gymService.deleteGym(id);  // Delete the gym by its ID
        return "redirect:/gym/list";  // Redirect to the gym list after deletion
    }
}
