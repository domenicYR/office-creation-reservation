package com.portfolioapps.officecreationreservation.Controller;

import com.portfolioapps.officecreationreservation.Reservation.Validation.DateFormValidation;
import com.portfolioapps.officecreationreservation.Office.Office;
import com.portfolioapps.officecreationreservation.Office.OfficeRepository;
import com.portfolioapps.officecreationreservation.Reservation.Reservation;
import com.portfolioapps.officecreationreservation.Reservation.ReservationRepository;
import com.portfolioapps.officecreationreservation.Room.Room;
import com.portfolioapps.officecreationreservation.Room.RoomRepository;
import com.portfolioapps.officecreationreservation.Reservation.Validation.TimeFormValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class AppController {
    // Class variable(s)
    private static Office currentOffice;
    private static Room currentRoom;
    private static Reservation currentReservation;

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
     * @return view show-offices
     */
    @GetMapping("/show-offices")
    public String showOffices(Model model) {
        model.addAttribute("dataOffices", this.officeRepository.findAllOfficesOrderASC());
        return "show-offices";
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
     * @param buttonValue
     * @param formData
     * @param bindingResult
     * @return view show-offices
     */
    @PostMapping("/add-office")
    public String handleAddOfficeFormData(@RequestParam(name = "action") String buttonValue,
                                          @Valid @ModelAttribute Office formData, BindingResult bindingResult) {

        if (buttonValue.equals("Add office")) {
            if (bindingResult.hasErrors()) {
                return "add-office";
            }

            formData.setOfficeName(formData.getOfficeName().trim());
            this.officeRepository.save(formData);
        }

        return "redirect:/show-offices";
    }

    /**
     * Handle request for deleting an office.
     *
     * @param id
     * @return view show-offices
     */
    @GetMapping("/delete-office/{id}")
    public String deleteOffice(@PathVariable("id") Integer id) {
        this.officeRepository.deleteById(id);
        return "redirect:/show-offices";
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
        currentOffice = this.officeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Id: " + id));

        model.addAttribute("currentOffice", currentOffice);
        model.addAttribute("dataRooms", this.roomRepository.findAllRoomsByForeignKey(id));

        return "show-rooms";
    }

    /**
     * Handle request for showing the form to add a room.
     *
     * @param model
     * @return view add-room
     */
    @GetMapping("/add-room")
    public String showAddRoomForm(Model model) {
        model.addAttribute("room", new Room());
        return "add-room";
    }

    /**
     * Handle request for storing the input of the form to add a room.
     *
     * @param buttonValue
     * @param roomFormData
     * @param bindingResult
     * @return view show-rooms
     */
    @PostMapping("/add-room")
    public String handleAddRoomFormData(@RequestParam(name = "action") String buttonValue,
                                        @Valid @ModelAttribute Room roomFormData, BindingResult bindingResult) {

        if (buttonValue.equals("Add room")) {
            if (bindingResult.hasErrors()) {
                return "add-room";
            }

            roomFormData.setRoomName(roomFormData.getRoomName().trim());
            roomFormData.setOffice(currentOffice);
            this.roomRepository.save(roomFormData);
        }

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
     * Handle request for adding a reservation.
     * There are two forms to make a reservation. The method returns
     * the name of the view to show the first form to input the date
     * for the reservation.
     *
     * @param id
     * @param model
     * @return view add-reservation-date
     */
    @GetMapping("/add-reservation-date/{id}")
    public String showReservationDateForm(@PathVariable("id") Integer id, Model model) {
        currentRoom = this.roomRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Id: " + id));

        model.addAttribute("currentOffice", currentOffice);
        model.addAttribute("currentRoom", currentRoom);
        model.addAttribute("reservation", new Reservation());

        return "add-reservation-date";
    }

    /**
     * Handle request for adding a reservation.
     * There are two forms to make a reservation. The method returns
     * the name of the view to show the second form to input the time
     * for the reservation.
     *
     * @param buttonValue
     * @param reservationFormData
     * @param bindingResult
     * @param model
     * @return view add-reservation-time
     */
    @GetMapping("/add-reservation-time")
    public String showReservationTimeForm(@RequestParam(name = "action") String buttonValue,
                                          @Validated(DateFormValidation.class) @ModelAttribute Reservation reservationFormData, BindingResult bindingResult,
                                          Model model) {

        if (buttonValue.equals("Select time")) {
            if (bindingResult.hasErrors()) {
                model.addAttribute("currentOffice", currentOffice);
                model.addAttribute("currentRoom", currentRoom);
                return "add-reservation-date";
            }

            currentReservation = reservationFormData;

            model.addAttribute("currentOffice", currentOffice);
            model.addAttribute("currentRoom", currentRoom);
            model.addAttribute("currentReservation", currentReservation);
            model.addAttribute("reservations", this.reservationRepository.findAllReservationsByDateAndRoomID(
                    currentReservation.getReservationDate(),
                    currentRoom.getId()));

            return "add-reservation-time";
        }

        return "redirect:/show-rooms/" + currentOffice.getId();
    }

    /**
     * Handle request for storing the input of the forms to add a reservation.
     *
     * @param buttonValue
     * @param reservationFormData
     * @param bindingResult
     * @param model
     * @return view show-rooms
     */
    @PostMapping("/reserve-room")
    public String handleAddReservationFormData(@RequestParam(name = "action") String buttonValue,
                                               @Validated(TimeFormValidation.class) @ModelAttribute Reservation reservationFormData,
                                               BindingResult bindingResult,
                                               Model model) {

        if (buttonValue.equals("Add reservation")) {
            if (bindingResult.hasErrors()) {
                model.addAttribute("currentOffice", currentOffice);
                model.addAttribute("currentRoom", currentRoom);
                model.addAttribute("currentReservation", currentReservation);
                model.addAttribute("reservations", this.reservationRepository.findAllReservationsByDateAndRoomID(
                        currentReservation.getReservationDate(),
                        currentRoom.getId()));
                return "add-reservation-time";
            }

            reservationFormData.setReservationDate(currentReservation.getReservationDate());
            reservationFormData.setRoom(currentRoom);

            this.reservationRepository.save(reservationFormData);
        }

        return "redirect:/show-rooms/" + currentOffice.getId();
    }
}
