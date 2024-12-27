package com.online.travel.planning.online.travel.planning.backend.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.online.travel.planning.online.travel.planning.backend.Model.Reservation;

public interface ReservationRepository extends MongoRepository <Reservation, String>{

     List<Reservation> findReservationByUserId(String userId);

}
