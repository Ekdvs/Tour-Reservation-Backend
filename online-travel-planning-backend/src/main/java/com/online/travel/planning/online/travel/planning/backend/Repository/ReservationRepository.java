package com.online.travel.planning.online.travel.planning.backend.Repository;

import com.online.travel.planning.online.travel.planning.backend.Model.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends MongoRepository<Reservation,String> {
    List<Reservation> findReservationByUserEmail(String userEmail);
    @Query(value = "{'reservationDate': ?0}", fields = "{'totalCharge': 1}")
    List<Reservation> findTotalChargesByDate(LocalDate date);
    @Query(value = "{'reservationDate': ?0}", fields = "{'numOfTickets': 1}")
    List<Reservation> findNumOfPersonByDate(LocalDate date);
    @Query(value = "{}", fields = "{'totalPrice': 1}")
    List<Reservation> findAllTotalPrice();
    @Query(value = "{}", fields = "{'numberOfPeople': 1}")
    List<Reservation> findAllNumberOfPeople();

}
