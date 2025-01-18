package com.online.travel.planning.online.travel.planning.backend.Service;

import com.online.travel.planning.online.travel.planning.backend.Model.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    Payment processPayment(Payment payment);
    void sendPaymentSuccessEmail(Payment payment);
    Optional<Payment> getPaymentById(String paymentId);
    void deletePayment(String paymentId);
    List<Payment> getAllPayments();
    List<Payment> getPaymentsByUserId(String userId);
    List<Payment> getPaymentsByReservationId(String reservationId);
}
