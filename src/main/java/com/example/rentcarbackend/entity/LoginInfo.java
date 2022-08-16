package com.example.rentcarbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "login_info")
public class LoginInfo {

    @Id
    @GeneratedValue
    @Column(name = "LoginInfoId", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @CreationTimestamp
    @Column(name = "created", nullable = false, updatable = false)
    private LocalDateTime logInTime;

    public LoginInfo(User user) {
        this.user = user;
    }

    public LoginInfo(User user, LocalDateTime logInTime) {

        this.user = user;
        this.logInTime = logInTime;
    }
}
