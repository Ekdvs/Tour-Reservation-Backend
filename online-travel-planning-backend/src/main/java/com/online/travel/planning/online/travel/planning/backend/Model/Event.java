package com.online.travel.planning.online.travel.planning.backend.Model;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "event")
public class Event {
    @Id
    private String eventId;
    private String eventName;
    private String eventDate;
    private String eventTime;
    
    
    


}
