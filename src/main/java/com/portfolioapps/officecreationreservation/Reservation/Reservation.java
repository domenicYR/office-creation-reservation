package com.portfolioapps.officecreationreservation.Reservation;

import com.portfolioapps.officecreationreservation.Reservation.Validation.DateFormValidation;
import com.portfolioapps.officecreationreservation.Reservation.Validation.TimeFormValidation;
import com.portfolioapps.officecreationreservation.Room.Room;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "reservations")
public class Reservation {
    // Field(s)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Please select a date", groups = DateFormValidation.class)
    @FutureOrPresent(message = "Date cannot be in the past", groups = DateFormValidation.class)
    @Column(name = "reservation_date")
    private LocalDate reservationDate;

    @NotEmpty(message = "Field may not be empty", groups = TimeFormValidation.class)
    @Column(name = "reservation_time_from")
    private String reservationTimeFrom;

    @NotEmpty(message = "Field may not be empty", groups = TimeFormValidation.class)
    @Column(name = "reservation_time_to")
    private String reservationTimeTo;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Room room;

    // Method(s)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getReservationTimeFrom() {
        return reservationTimeFrom;
    }

    public void setReservationTimeFrom(String reservationTimeFrom) {
        this.reservationTimeFrom = reservationTimeFrom;
    }

    public String getReservationTimeTo() {
        return reservationTimeTo;
    }

    public void setReservationTimeTo(String reservationTimeTo) {
        this.reservationTimeTo = reservationTimeTo;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
