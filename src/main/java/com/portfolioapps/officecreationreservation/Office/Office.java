package com.portfolioapps.officecreationreservation.Office;

import jakarta.persistence.*;

@Entity
@Table(name = "offices")
public class Office {
    // Field(s)
    // "id" field corresponds primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "officeName")
    private String officeName;
    @Column(name = "officeOpeningTimes")
    private String officeOpeningTimes;

    // Method(s)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getOfficeOpeningTimes() {
        return officeOpeningTimes;
    }

    public void setOfficeOpeningTimes(String officeOpeningTimes) {
        this.officeOpeningTimes = officeOpeningTimes;
    }
}
