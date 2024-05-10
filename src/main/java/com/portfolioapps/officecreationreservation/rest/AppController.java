package com.portfolioapps.officecreationreservation.rest;

import com.portfolioapps.officecreationreservation.OfficeCreationForm;
import com.portfolioapps.officecreationreservation.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AppController {
    // Field(s)
    private OfficeRepository officeRepository;

    // Constructor(s)
    @Autowired
    public AppController(OfficeRepository repository) {
        this.officeRepository = repository;
    }

    // Method(s)
    @GetMapping("/")
    public String displayStartPage() {
        return "index";
    }

    @GetMapping("/offices")
    public String displayOffices(Model model) {
        model.addAttribute("dataOffices", officeRepository.findAll());
        return "offices";
    }

    @GetMapping("/officeCreation")
    public String createOfficeForm(Model model) {
        model.addAttribute("office", new OfficeCreationForm());
        return "officeCreation";
    }

    @PostMapping("/officeCreation")
    public String createOfficeSubmit(@ModelAttribute OfficeCreationForm formData, Model model) {
        officeRepository.save(formData);
        model.addAttribute("office", formData);
        return "result";
    }

    // handle request for showing rooms
    @GetMapping("/show-rooms/{id}")
    public String displayRooms(@PathVariable("id") Integer id, Model model) {
        OfficeCreationForm officeWithRequestedID = officeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Id: " + id));

        model.addAttribute("officeWithRequestedID", officeWithRequestedID);

        return "show-rooms";
    }
}
