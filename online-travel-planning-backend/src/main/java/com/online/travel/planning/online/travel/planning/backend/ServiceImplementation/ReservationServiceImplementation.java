package com.online.travel.planning.online.travel.planning.backend.ServiceImplementation;

import com.online.travel.planning.online.travel.planning.backend.Model.Reservation;
import com.online.travel.planning.online.travel.planning.backend.Repository.ReservationRepository;
import com.online.travel.planning.online.travel.planning.backend.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImplementation implements ReservationService {
    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public Reservation addReservation(Reservation reservation) {
        
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation>getReservationsByUserEmail(String userId){
        return reservationRepository.findReservationByUserEmail(userId);
    }

    @Override
    public Optional<Reservation> getReservationById(String reservationId) {
        return reservationRepository.findById(reservationId);
    }

    @Override
    public Reservation updateReservation(String reservationId, Reservation reservationDetail) {
        return reservationRepository.findById(reservationId).map(reservation -> {
            reservation.setPackageId(reservationDetail.getPackageId());
            reservation.setUserEmail(reservationDetail.getUserEmail());
            reservation.setReservationDate(reservationDetail.getReservationDate());
            reservation.setReservationTime(reservationDetail.getReservationTime());
            reservation.setNumberOfPeople(reservationDetail.getNumberOfPeople());
            reservation.setTotalPrice(reservationDetail.getTotalPrice());
            reservation.setStatus(reservationDetail.getStatus());
            reservation.setTravelGuide(reservationDetail.getTravelGuide());
            return reservationRepository.save(reservation);
        }).orElseThrow(() -> new RuntimeException("Reservation not found with id " + reservationId));
    }

    @Override
    public void deleteReservation(String reservationId) {
        reservationRepository.deleteById(reservationId);
    }

    @Override
    public double getTotalPriceByCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        return reservationRepository.findTotalChargesByDate(currentDate)
                .stream()
                .mapToDouble(Reservation::getTotalPrice)
                .sum();
    }

    @Override
    public double getTotalPrice() {
        return reservationRepository.findAllTotalPrice()
                .stream()
                .mapToDouble(Reservation::getTotalPrice)
                .sum();
    }







}
