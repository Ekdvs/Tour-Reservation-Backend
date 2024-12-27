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

    public String getReservationId() {
        return reservationId;
    }

    public String getUserId() {
        return userId;
    }

    public String getPackgeId() {
        return packgeId;
    }

    public Integer getNumOfPerson() {
        return numOfPerson;
    }

    public Double getTotalCharge() {
        return totalCharge;
    }

    public Double getPerPersonCharge() {
        return perPersonCharge;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public LocalTime getReservationTime() {
        return reservationTime;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPackgeId(String packgeId) {
        this.packgeId = packgeId;
    }

    public void setNumOfPerson(Integer numOfPerson) {
        this.numOfPerson = numOfPerson;
    }

    public void setTotalCharge(Double totalCharge) {
        this.totalCharge = totalCharge;
    }

    public void setPerPersonCharge(Double perPersonCharge) {
        this.perPersonCharge = perPersonCharge;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public void setReservationTime(LocalTime reservationTime) {
        this.reservationTime = reservationTime;
    }



    

}
