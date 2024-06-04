package com.portfolioapps.officecreationreservation.Room;

import com.portfolioapps.officecreationreservation.Office.Office;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "rooms")
public class Room {
    // Field(s)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name may not be empty")
    @Column(name = "room_name")
    private String roomName;
    @ManyToOne
    @JoinColumn(name = "office_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Office office;

    // Method(s)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }
}
