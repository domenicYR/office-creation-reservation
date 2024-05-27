package com.portfolioapps.officecreationreservation.Controller;

import com.portfolioapps.officecreationreservation.Reservation.Reservation;
import com.portfolioapps.officecreationreservation.Reservation.ReservationRepository;
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
    private static Room currentRoom;

    // Field(s)
    private OfficeRepository officeRepository;
    private RoomRepository roomRepository;
    private ReservationRepository reservationRepository;

    // Constructor(s)
    @Autowired
    public AppController(OfficeRepository officeRepository, RoomRepository roomRepository, ReservationRepository reservationRepository) {
        this.officeRepository = officeRepository;
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
    }

    // Method(s)
    @GetMapping("/")
    public String showStartPage() {
        return "index";
    }

    @GetMapping("/offices")
    public String showOffices(Model model) {
        model.addAttribute("dataOffices", officeRepository.findAllOfficesOrderASC());
        return "offices";
    }

    // handle request for showing form to add an office
    @GetMapping("/add-office")
    public String showAddOfficeForm(Model model) {
        model.addAttribute("office", new Office());
        return "add-office";
    }

    @PostMapping("/add-office")
    public String handleAddOfficeFormData(@ModelAttribute Office formData) {
        officeRepository.save(formData);
        return "redirect:/offices";
    }

    // handle request for showing rooms to an office
    @GetMapping("/show-rooms/{id}")
    public String showRooms(@PathVariable("id") Integer id, Model model) {
        currentOffice = officeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Id: " + id));

        model.addAttribute("currentOffice", currentOffice);
        model.addAttribute("dataRooms", roomRepository.findAllRoomsByForeignKey(id));

        return "show-rooms";
    }

    // handle request for deleting an office
    @GetMapping("/delete-office/{id}")
    public String deleteOffice(@PathVariable("id") Integer id) {
        this.officeRepository.deleteById(id);
        return "redirect:/offices";
    }

    // handle request for showing form to add a room
    @GetMapping("/show-rooms/add-room")
    public String showAddRoomForm(Model model) {
        model.addAttribute("room", new Room());
        return "add-room";
    }

    @PostMapping("/show-rooms/add-room")
    public String handleAddRoomFormData(@ModelAttribute Room roomFormData) {
        roomFormData.setOffice(currentOffice);
        this.roomRepository.save(roomFormData);
        return "redirect:/show-rooms/" + currentOffice.getId();
    }

    // handle request for deleting a room
    @GetMapping("/delete-room/{id}")
    public String deleteRoom(@PathVariable("id") Integer id) {
        this.roomRepository.deleteById(id);
        return "redirect:/show-rooms/" + currentOffice.getId();
    }

    // handle request for showing form to add a reservation
    @GetMapping("/reserve-room/{id}")
    public String reserveRoom(@PathVariable("id") Integer id, Model model) {
        currentRoom = this.roomRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Id: " + id));

        model.addAttribute("currentRoom", currentRoom);
        model.addAttribute("reservation", new Reservation());

        return "add-reservation";
    }

    @PostMapping("/reserve-room")
    public String handleAddReservationFormData(@ModelAttribute Reservation reservationFormData) {
        reservationFormData.setRoom(currentRoom);
        this.reservationRepository.save(reservationFormData);
        return "redirect:/show-rooms/" + currentOffice.getId();
    }
}
