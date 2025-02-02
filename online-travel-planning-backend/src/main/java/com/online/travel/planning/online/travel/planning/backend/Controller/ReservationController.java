package com.online.travel.planning.online.travel.planning.backend.Controller;


import com.online.travel.planning.online.travel.planning.backend.Model.Payment;
import com.online.travel.planning.online.travel.planning.backend.Model.Reservation;
import com.online.travel.planning.online.travel.planning.backend.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
