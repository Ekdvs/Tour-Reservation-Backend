package com.online.travel.planning.online.travel.planning.backend.Controller;


import com.online.travel.planning.online.travel.planning.backend.Model.Payment;
import com.online.travel.planning.online.travel.planning.backend.Model.Reservation;
import com.online.travel.planning.online.travel.planning.backend.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired

    private ReservationService reservationService;

    // Create a new resevation
    @PostMapping("/create")
    public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation) {
        try {
            Reservation newresevation = reservationService.addReservation(reservation);
            return ResponseEntity.ok(newresevation);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/getAllReservations")
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/getReservationById/{id}")
    public Optional<Reservation> getReservationById(@PathVariable("id") String reservationId) {
        return reservationService.getReservationById(reservationId);
    }

    @GetMapping("/getReservationByUserId/{id}")
    public List<Reservation> getReservationByUserId(@PathVariable("id") String userEmail) {
        return reservationService.getReservationsByUserEmail(userEmail);
    }

    @PutMapping("/updateReservation/{id}")
    public Reservation updateReservation(@PathVariable("id") String reservationId,
                                         @RequestBody Reservation reservation) {
        return reservationService.updateReservation(reservationId, reservation);
    }

    @DeleteMapping("/deleteReservation/{id}")
    public String deleteReservation(@PathVariable("id") String reservationId) {
        reservationService.deleteReservation(reservationId);
        return "Match deleted with ID " + reservationId;
    }
    @GetMapping("/totalCharge/today")
    public ResponseEntity<Double> getTotalChargeByCurrentDate() {
        return ResponseEntity.ok(reservationService.getTotalPriceByCurrentDate());
    }

    @GetMapping("/totalCharge")
    public ResponseEntity<Double> getTotalCharge() {
        return ResponseEntity.ok(reservationService.getTotalPrice());
    }


}
