package com.online.travel.planning.online.travel.planning.backend.ServiceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.travel.planning.online.travel.planning.backend.Model.Reservation;
import com.online.travel.planning.online.travel.planning.backend.Repository.ReservationRepository;
import com.online.travel.planning.online.travel.planning.backend.Service.ReservationService;

import io.swagger.v3.oas.annotations.servers.Servers;

@Service
public class ReservationServiceImplementation 

    @Autowired
    private ReservationRepository reservationRepository;

    public ReservationServiceImplementation(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> getReservationByUserId(String userId){
        return reservationRepository.findReservationByUserId(userId);
    }

    @Override
    public Optional<Reservation> getReservationById(String reservationId) {
        return reservationRepository.findById(reservationId);
    }

}
