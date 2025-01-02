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
    private String eventVenue;
    private String eventOrganizer;
    private String description;
    private Double oneTicketPrice;
    private String eventType;
    private String eventIsFor;
    private String eventImagePath;
    private Integer numOfTickets;
    private String contentType;
    private byte[] imageData;
    
    
    
    
    
    
    
    
   
    
    
    
    


}
