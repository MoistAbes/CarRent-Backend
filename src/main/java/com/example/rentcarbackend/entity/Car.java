package com.example.rentcarbackend.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "rentedCars")
public class RentedCar {

    @Id
    @GeneratedValue
    @Column(name = "rentedCarId", unique = true)
    private Long id;

    @NotNull
    @Column(name = "year")
    private int year;

    @NotNull
    @Column(name = "brand")
    private String brand;

    @NotNull
    @Column(name = "model")
    private String model;

    @NotNull
    @Column(name = "type")
    private String type;

    @OneToMany(
            cascade = CascadeType.REMOVE,
            targetEntity = Rent.class,
            mappedBy = "rentedCar",
            fetch = FetchType.LAZY
    )
    private List<Rent> rents = new ArrayList<>();

    public RentedCar(int year, String brand, String model, String type) {
        this.year = year;
        this.brand = brand;
        this.model = model;
        this.type = type;
    }

    public RentedCar(Long id, int year, String brand, String model, String type) {
        this.id = id;
        this.year = year;
        this.brand = brand;
        this.model = model;
        this.type = type;
    }
}
