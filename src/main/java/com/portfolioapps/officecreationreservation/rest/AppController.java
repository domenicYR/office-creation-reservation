package com.portfolioapps.officecreationreservation.rest;

import com.portfolioapps.officecreationreservation.AddRoomForm;
import com.portfolioapps.officecreationreservation.OfficeCreationForm;
import com.portfolioapps.officecreationreservation.OfficeRepository;
import com.portfolioapps.officecreationreservation.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AppController {
    private static OfficeCreationForm currentOffice;

    // Field(s)
    private OfficeRepository officeRepository;
    private RoomRepository roomRepository;

    // Constructor(s)
    @Autowired
    public AppController(OfficeRepository officeRepository, RoomRepository roomRepository) {
        this.officeRepository = officeRepository;
        this.roomRepository = roomRepository;
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

    // handle request for showing rooms to an office
    @GetMapping("/show-rooms/{id}")
    public String displayRooms(@PathVariable("id") Integer id, Model model) {
        currentOffice = officeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Id: " + id));

        model.addAttribute("currentOffice", currentOffice);

        return "show-rooms";
    }

    // handle request for deleting an office
    @GetMapping("/delete-rooms/{id}")
    public String deleteOffice(@PathVariable("id") Integer id) {
        this.officeRepository.deleteById(id);
        return "redirect:/offices";
    }

    // handle request for showing form to add a room
    @GetMapping("/show-rooms/add-room")
    public String displayAddRoomForm(Model model) {
        model.addAttribute("room", new AddRoomForm());
        return "add-room";
    }

    @PostMapping("/show-rooms/add-room")
    public String handleAddRoomFormData(@ModelAttribute AddRoomForm roomFormData, Model model) {
        roomFormData.setOffice(currentOffice);
        this.roomRepository.save(roomFormData);
        model.addAttribute("room", roomFormData);
        return "resultRoom";
    }
}
