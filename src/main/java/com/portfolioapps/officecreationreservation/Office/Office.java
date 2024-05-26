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
    @Column(name = "officeOpeningTime")
    private String officeOpeningTime;
    @Column(name = "officeClosingTime")
    private String officeClosingTime;

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

    public String getOfficeOpeningTime() {
        return officeOpeningTime;
    }

    public void setOfficeOpeningTime(String officeOpeningTime) {
        this.officeOpeningTime = officeOpeningTime;
    }

    public String getOfficeClosingTime() {
        return officeClosingTime;
    }

    public void setOfficeClosingTime(String officeClosingTime) {
        this.officeClosingTime = officeClosingTime;
    }
}
