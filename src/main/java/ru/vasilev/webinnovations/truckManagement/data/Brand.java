package ru.vasilev.webinnovations.truckManagement.data;

import javax.persistence.*;

@Entity
@Table
public class Brand {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String brandName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
