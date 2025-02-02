package com.online.travel.planning.online.travel.planning.backend.ServiceImplementation;

import com.online.travel.planning.online.travel.planning.backend.Model.Reservation;
import com.online.travel.planning.online.travel.planning.backend.Repository.ReservationRepository;
import com.online.travel.planning.online.travel.planning.backend.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImplementation implements ReservationService {
    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }
}
