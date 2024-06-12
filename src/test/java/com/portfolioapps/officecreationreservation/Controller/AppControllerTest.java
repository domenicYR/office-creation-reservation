package com.portfolioapps.officecreationreservation.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolioapps.officecreationreservation.Office.OfficeRepository;
import com.portfolioapps.officecreationreservation.Reservation.ReservationRepository;
import com.portfolioapps.officecreationreservation.Room.RoomRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AppController.class)
class AppControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OfficeRepository officeRepository;
    @MockBean
    private RoomRepository roomRepository;
    @MockBean
    private ReservationRepository reservationRepository;

    @Test
    void shouldReturn200ForRequestToShowStartPage() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }

    // *****************
    // Office Controller
    // *****************
    @Test
    void shouldReturn200ForRequestToShowOffices() throws Exception {
        mockMvc.perform(get("/show-offices")).andExpect(status().isOk());
    }

    @Test
    void shouldReturn200ForRequestToShowAddOfficeForm() throws Exception {
        mockMvc.perform(get("/add-office")).andExpect(status().isOk());
    }

    @Test
    void shouldReturn200ForRequestToAddOffice() throws Exception {
        mockMvc.perform(post("/add-office").param("action", "Add office"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturn200ForRequestToDeleteOffice() throws Exception {
        mockMvc.perform(get("/delete-office/{id}", 1))
                .andExpect(status().is(302));
    }

    // *****************************************************************************************************************

    // ***************
    // Room Controller
    // ***************
    @Test
    void shouldReturn200ForRequestToShowAddRoomForm() throws Exception {
        mockMvc.perform(get("/add-room"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturn200ForRequestToAddRoom() throws Exception {
        mockMvc.perform(post("/add-room").param("action", "Add room"))
                .andExpect(status().isOk());
    }
}
