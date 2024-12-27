package com.online.travel.planning.online.travel.planning.backend.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.online.travel.planning.online.travel.planning.backend.Model.Reservation;

public interface ReservationRepository extends MongoRepository <Reservation, String>{

     List<Reservation> findReservationByUserId(String userId);
      @Query(value = "{'reservationDate': ?0}", fields = "{'totalCharge': 1}")
    List<Reservation> findTotalChargesByDate(LocalDate date);
    @Query(value = "{'reservationDate': ?0}", fields = "{'numOfTickets': 1}")
    List<Reservation> findNumOfPersonByDate(LocalDate date);
    @Query(value = "{}", fields = "{'totalCharge': 1}")
    List<Reservation> findAllTotalCharges();

   
   
   

}
