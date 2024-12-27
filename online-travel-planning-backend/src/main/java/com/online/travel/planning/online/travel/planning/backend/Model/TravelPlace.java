package com.online.travel.planning.online.travel.planning.backend.Model;

public class TravelPlace {
    @Id
    private String placeId;
    private String placeName;
    private String location;
    private String description;
    private Double price;
    private LocalDate dateAdded = LocalDate.now();
    private LocalTime timeAdded = LocalTime.now();


}
