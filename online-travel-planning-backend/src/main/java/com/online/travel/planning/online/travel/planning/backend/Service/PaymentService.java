package com.online.travel.planning.online.travel.planning.backend.Service;

public interface PaymentService {
    Payment processPayment(Payment payment);
    void sendPaymentSuccessEmail(Payment payment);
    Optional<Payment> getPaymentById(String paymentId);
    void deletePayment(String paymentId);



}
