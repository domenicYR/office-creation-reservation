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

    // *****************
    // Office Controller
    // *****************

    /**
     * Handle request for showing all offices.
     *
     * @param model
     * @return view offices
     */
    @GetMapping("/offices")
    public String showOffices(Model model) {
        model.addAttribute("dataOffices", officeRepository.findAllOfficesOrderASC());
        return "offices";
    }

    /**
     * Handle request for showing the form to add an office.
     *
     * @param model
     * @return view add-office
     */
    @GetMapping("/add-office")
    public String showAddOfficeForm(Model model) {
        model.addAttribute("office", new Office());
        return "add-office";
    }

    /**
     * Handle request for storing the input of the form to add an office.
     *
     * @param formData
     * @return view offices
     */
    @PostMapping("/add-office")
    public String handleAddOfficeFormData(@ModelAttribute Office formData) {
        officeRepository.save(formData);
        return "redirect:/offices";
    }

    /**
     * Handle request for deleting an office.
     *
     * @param id
     * @return view offices
     */
    @GetMapping("/delete-office/{id}")
    public String deleteOffice(@PathVariable("id") Integer id) {
        this.officeRepository.deleteById(id);
        return "redirect:/offices";
    }

    // *****************************************************************************************************************

    // ***************
    // Room Controller
    // ***************

    /**
     * Handle request for showing all rooms to a specific office.
     *
     * @param id
     * @param model
     * @return view show-rooms
     */
    @GetMapping("/show-rooms/{id}")
    public String showRooms(@PathVariable("id") Integer id, Model model) {
        currentOffice = officeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Id: " + id));

        model.addAttribute("currentOffice", currentOffice);
        model.addAttribute("dataRooms", roomRepository.findAllRoomsByForeignKey(id));

        return "show-rooms";
    }

    /**
     * Handle request for showing the form to add a room.
     *
     * @param model
     * @return view add-room
     */
    @GetMapping("/show-rooms/add-room")
    public String showAddRoomForm(Model model) {
        model.addAttribute("room", new Room());
        return "add-room";
    }

    /**
     * Handle request for storing the input of the form to add a room.
     *
     * @param roomFormData
     * @return view show-rooms
     */
    @PostMapping("/show-rooms/add-room")
    public String handleAddRoomFormData(@ModelAttribute Room roomFormData) {
        roomFormData.setOffice(currentOffice);
        this.roomRepository.save(roomFormData);
        return "redirect:/show-rooms/" + currentOffice.getId();
    }

    /**
     * Handle request for deleting a room.
     *
     * @param id
     * @return view show-rooms
     */
    @GetMapping("/delete-room/{id}")
    public String deleteRoom(@PathVariable("id") Integer id) {
        this.roomRepository.deleteById(id);
        return "redirect:/show-rooms/" + currentOffice.getId();
    }

    // *****************************************************************************************************************

    // **********************
    // Reservation Controller
    // **********************

    /**
     * Handle request for showing the form to add a reservation.
     *
     * @param id
     * @param model
     * @return view add-reservation
     */
    @GetMapping("/reserve-room/{id}")
    public String reserveRoom(@PathVariable("id") Integer id, Model model) {
        currentRoom = this.roomRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Id: " + id));

        model.addAttribute("currentRoom", currentRoom);
        model.addAttribute("reservation", new Reservation());

        return "add-reservation";
    }

    /**
     * Handle request for storing the input of the form to add a reservation.
     *
     * @param reservationFormData
     * @return view show-rooms
     */
    @PostMapping("/reserve-room")
    public String handleAddReservationFormData(@ModelAttribute Reservation reservationFormData) {
        reservationFormData.setRoom(currentRoom);
        this.reservationRepository.save(reservationFormData);
        return "redirect:/show-rooms/" + currentOffice.getId();
    }
}
