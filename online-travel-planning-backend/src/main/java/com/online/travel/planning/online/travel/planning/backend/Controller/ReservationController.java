package com.online.travel.planning.online.travel.planning.backend.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.travel.planning.online.travel.planning.backend.Model.Reservation;
import com.online.travel.planning.online.travel.planning.backend.Service.ReservationService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
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
    public List<Reservation> getReservationByUserId(@PathVariable("id") String userId) {
        return reservationService.getReservationByUserId(userId);
    }

    @PostMapping("/addReservation")
    public Reservation addReservation(@RequestBody Reservation reservation) {
        return reservationService.addReservation(reservation);
    }

    @PutMapping("/updateReservation/{id}")
    public Reservation updateReservation(@PathVariable("id") String reservationId,
                                         @RequestBody Reservation reservation) {
        return reservationService.updateReservation(reservationId, reservation);
    }

    

}
