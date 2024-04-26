package com.portfolioapps.officecreationreservation.rest;

import com.portfolioapps.officecreationreservation.OfficeCreationForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AppController {
    @GetMapping("/")
    public String displayStartPage() {
        return "index";
    }

    @GetMapping("/offices")
    public String displayOffices() {
        return "offices";
    }

    @GetMapping("/officeCreation")
    public String createOfficeForm(Model model) {
        model.addAttribute("office", new OfficeCreationForm());
        return "officeCreation";
    }

    @PostMapping("/officeCreation")
    public String createOfficeSubmit(@ModelAttribute OfficeCreationForm formData, Model model) {
        model.addAttribute("office", formData);
        return "result";
    }
}
