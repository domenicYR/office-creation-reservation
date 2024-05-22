package com.portfolioapps.officecreationreservation;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "rooms")
public class AddRoomForm {
    // Field(s)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "roomName")
    private String roomName;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private OfficeCreationForm office;

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

    public OfficeCreationForm getOffice() {
        return office;
    }

    public void setOffice(OfficeCreationForm office) {
        this.office = office;
    }
}
