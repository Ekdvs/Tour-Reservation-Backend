package com.online.travel.planning.online.travel.planning.backend.Model;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reservation")
public class Reservation {
    @Id
    private String reservationId;
    private String userId;
    private String packgeId;
    private Integer numOfPerson;
    private Double totalCharge;
    private Double perPersonCharge;
    private String status = "Available";
    private LocalDate reservationDate = LocalDate.now();
    private LocalTime reservationTime = LocalTime.now();



    

}
