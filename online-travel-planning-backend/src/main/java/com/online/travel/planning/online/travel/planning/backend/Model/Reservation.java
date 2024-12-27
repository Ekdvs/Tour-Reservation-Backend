package com.online.travel.planning.online.travel.planning.backend.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reservation")
public class Reservation {
    @Id
    private String reservationId;

}
