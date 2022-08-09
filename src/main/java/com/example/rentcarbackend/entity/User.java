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
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "userId", unique = true)
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "surname")
    private String surname;

    @NotNull
    @Column(name = "username")
    private String username;

    @NotNull
    @Column(name = "password")
    private String password;

    @OneToMany(
            targetEntity = Rent.class,
            mappedBy = "user",
            fetch = FetchType.LAZY
    )
    private List<Rent> rents = new ArrayList<>();

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public User(Long id, String name, String surname, String username, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
    }
}
