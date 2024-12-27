package com.online.travel.planning.online.travel.planning.backend.Service;

import java.util.List;

import com.online.travel.planning.online.travel.planning.backend.Model.Reservation;

public interface ReservationService {
    List<Reservation> getAllReservations();
    List<Reservation> getReservationByUserId(String userId);
    Optional<Reservation> getReservationById(String reservationId);
}
