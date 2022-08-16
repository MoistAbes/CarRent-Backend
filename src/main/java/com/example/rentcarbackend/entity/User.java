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
    @Column(name = "password", unique = true)
    private String password;

    @OneToMany(
            targetEntity = Rent.class,
            mappedBy = "user",
            fetch = FetchType.LAZY
    )
    private List<Rent> rents = new ArrayList<>();

    @OneToMany(
            targetEntity = LoginInfo.class,
            mappedBy = "user",
            fetch = FetchType.EAGER
    )
    private List<LoginInfo> loginInfos = new ArrayList<>();

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public User(String name, String surname, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
    }

    public User(Long id, String name, String surname, String username, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
    }

    public void setLoginInfos(List<LoginInfo> loginInfos) {
        this.loginInfos = loginInfos;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", rents=" + rents +
                ", loginInfos=" + loginInfos +
                '}';
    }
}
