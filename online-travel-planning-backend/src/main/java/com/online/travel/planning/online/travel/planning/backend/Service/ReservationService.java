package com.online.travel.planning.online.travel.planning.backend.Service;

import com.online.travel.planning.online.travel.planning.backend.Model.Payment;
import com.online.travel.planning.online.travel.planning.backend.Model.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    Reservation addReservation(Reservation reservation);
    List<Reservation> getAllReservations();
    List<Reservation> getReservationsByUserEmail(String userEmail);
    Optional<Reservation> getReservationById(String reservationId);
    Reservation updateReservation(String reservationId,Reservation reservation);
    void deleteReservation(String reservationId);
    double getTotalPriceByCurrentDate();
    double getTotalPrice();

}
