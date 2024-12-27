package com.online.travel.planning.online.travel.planning.backend.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "user")

public class User {
    @Id
    private String userId;
    private String firstName;
    private String lastName;
    private String userEmail;
    private String password;
    private String phoneNumber;
    private String userRole="user";
    private String title;
    private String gender;
    private String country;
    private String profilePictureUrl;
    private LocalDate dateRegistered =LocalDate.now();
    private LocalTime timeRegistered =LocalTime.now();
    private LocalDateTime lastLogin;

   }
