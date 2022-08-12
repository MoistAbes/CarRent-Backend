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

    @ManyToOne
    @JoinColumn(name = "rentedCarId")
    private RentedCar rentedCar;

    @NotNull
    @Column(name = "rentedFrom")
    private LocalDate rentedFrom;

    @NotNull
    @Column(name = "rentedTo")
    private LocalDate rentedTo;

    public Rent(User user,RentedCar rentedCar, LocalDate rentedFrom, LocalDate rentedTo) {
        this.user = user;
        this.rentedCar = rentedCar;
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
