package com.online.travel.planning.online.travel.planning.backend.Service;

import java.util.List;
import java.util.Optional;

import com.online.travel.planning.online.travel.planning.backend.Model.Payment;

public interface PaymentService {
    Payment processPayment(Payment payment);
    void sendPaymentSuccessEmail(Payment payment);
    Optional<Payment> getPaymentById(String paymentId);
    void deletePayment(String paymentId);
    List<Payment> getAllPayment();
    List<Payment> getPaymentByUserId(String userId);
    List<Payment> getPaymentByReservationId(String reservationId);

}
