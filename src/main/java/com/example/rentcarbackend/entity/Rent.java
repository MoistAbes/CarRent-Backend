package com.example.rentcarbackend.entity;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "rents")
public class Rent {


    @Id
    @GeneratedValue
    @Column(name = "rentId", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

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

    @NotNull
    @Column(name = "rentedFrom")
    private LocalDate rentedFrom;

    @NotNull
    @Column(name = "rentedTo")
    private LocalDate rentedTo;

    public Rent(User user, int year, String brand, String model, String type, LocalDate rentedFrom, LocalDate rentedTo) {
        this.user = user;
        this.year = year;
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.rentedFrom = rentedFrom;
        this.rentedTo = rentedTo;
    }

    public void setRentedFrom(LocalDate rentedFrom) {
        this.rentedFrom = rentedFrom;
    }

    public void setRentedTo(LocalDate rentedTo) {
        this.rentedTo = rentedTo;
    }
}
