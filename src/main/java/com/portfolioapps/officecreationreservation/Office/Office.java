package com.portfolioapps.officecreationreservation.Office;

import jakarta.persistence.*;

@Entity
@Table(name = "offices")
public class Office {
    // Field(s)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "office_name")
    private String officeName;
    @Column(name = "office_opening_time")
    private String officeOpeningTime;
    @Column(name = "office_closing_time")
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
