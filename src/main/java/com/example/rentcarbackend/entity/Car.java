package com.example.rentcarbackend.entity;

import com.example.rentcarbackend.domain.CarRentStatus;
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
@Entity(name = "cars")
public class Car {

    @Id
    @GeneratedValue
    @Column(name = "carId", unique = true)
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

    @Column(name = "isRented")
    private CarRentStatus status = CarRentStatus.RENTED;

    @OneToMany(
            cascade = CascadeType.REMOVE,
            targetEntity = Rent.class,
            mappedBy = "car",
            fetch = FetchType.LAZY
    )
    private List<Rent> rents = new ArrayList<>();

    public Car(int year, String brand, String model, String type, CarRentStatus status) {
        this.year = year;
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.status = status;
    }

    public Car(Long id, int year, String brand, String model, String type, CarRentStatus status) {
        this.id = id;
        this.year = year;
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.status = status;
    }

    public void setStatus(CarRentStatus status) {
        this.status = status;
    }
}
