package com.portfolioapps.officecreationreservation.Controller;

import com.portfolioapps.officecreationreservation.Room.Room;
import com.portfolioapps.officecreationreservation.Office.Office;
import com.portfolioapps.officecreationreservation.Office.OfficeRepository;
import com.portfolioapps.officecreationreservation.Room.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AppController {
    private static Office currentOffice;

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

    @GetMapping("/office-creation")
    public String createOfficeForm(Model model) {
        model.addAttribute("office", new Office());
        return "office-creation";
    }

    @PostMapping("/office-creation")
    public String createOfficeSubmit(@ModelAttribute Office formData, Model model) {
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
        model.addAttribute("room", new Room());
        return "add-room";
    }

    @PostMapping("/show-rooms/add-room")
    public String handleAddRoomFormData(@ModelAttribute Room roomFormData, Model model) {
        roomFormData.setOffice(currentOffice);
        this.roomRepository.save(roomFormData);
        model.addAttribute("room", roomFormData);
        return "resultRoom";
    }
}
