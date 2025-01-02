package com.online.travel.planning.online.travel.planning.backend.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "packages")
public class Packages {
    @Id
    private String id;
    private String name;
    private String description;
    private double onePersonPrice;
    private int duration; // in days
    
    
    
   



}
