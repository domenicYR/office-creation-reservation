package com.portfolioapps.officecreationreservation.Reservation;

import com.portfolioapps.officecreationreservation.Room.Room;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "reservations")
public class Reservation {
    // Field(s)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "reservationDate")
    private String reservationDate;
    @Column(name = "reservationTimeFrom")
    private String reservationTimeFrom;
    @Column(name = "reservationTimeTo")
    private String reservationTimeTo;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Room room;

    // Method(s)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
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
