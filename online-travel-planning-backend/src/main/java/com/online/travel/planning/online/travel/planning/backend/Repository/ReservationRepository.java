package com.online.travel.planning.online.travel.planning.backend.Repository;

import com.online.travel.planning.online.travel.planning.backend.Model.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends MongoRepository<Reservation,String> {
    List<Reservation> findByReservationByUserEmail(String userEmail);
    @Query(value="{ 'reservationDate':?0}",fields = "{'totalPrice':1}")
    List<Reservation> findByReservationByDate(LocalDate date);

}
