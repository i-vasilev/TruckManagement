package ru.vasilev.webinnovations.truckManagement.data;

import javax.persistence.*;

@Entity
@Table
public class Bodywork {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @ManyToOne
    private Brand brand;

    private String model;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
