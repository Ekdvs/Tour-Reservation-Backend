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
    private String location;
    private String imageUrl;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getOnePersonPrice() {
        return onePersonPrice;
    }

    public void setOnePersonPrice(double onePersonPrice) {
        this.onePersonPrice = onePersonPrice;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImageUrl() {
        return imageUrl;
    }




   
    
    
   



}
