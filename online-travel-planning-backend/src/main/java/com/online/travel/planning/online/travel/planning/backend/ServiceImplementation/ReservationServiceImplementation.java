package com.online.travel.planning.online.travel.planning.backend.ServiceImplementation;

import java.time.LocalDate;
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

    @Override
    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation updateReservation(String reservationId, Reservation reservationDetail) {
        return reservationRepository.findById(reservationId).map(reservation -> {
            reservation.setPackgeId(reservationDetail.getPackgeId());
            reservation.setUserId(reservationDetail.getUserId());
            reservation.setReservationDate(reservationDetail.getReservationDate());
            reservation.setReservationTime(reservationDetail.getReservationTime());
            reservation.setNumOfPerson(reservationDetail.getNumOfPerson());
            reservation.setPerPersonCharge(reservationDetail.getPerPersonCharge());
            reservation.setTotalCharge(reservationDetail.getTotalCharge());
            reservation.setStatus(reservationDetail.getStatus());
            return reservationRepository.save(reservation);
        }).orElseThrow(() -> new RuntimeException("Reservation not found with id " + reservationId));
    }

    @Override
    public void deleteReservation(String reservationId) {
        reservationRepository.deleteById(reservationId);
    }

     @Override
    public double getTotalChargeByCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        return reservationRepository.findTotalChargesByDate(currentDate)
                .stream()
                .mapToDouble(Reservation::getTotalCharge)
                .sum();
    }

    @Override
    public double getTotalCharge() {
        return reservationRepository.findAllTotalCharges()
                .stream()
                .mapToDouble(Reservation::getTotalCharge)
                .sum();
    }

    @Override
    public int getTotalPersonByCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        return reservationRepository.findNumOfPersonByDate(currentDate)
                .stream()
                .mapToInt(Reservation::getNumOfPerson)
                .sum();
    }

    @Override
    public int getTotalPerson() {
        return reservationRepository.findAllNumOfPerson()
                .stream()
                .mapToInt(Reservation::getNumOfPerson)
                .sum();
    }


    

}
