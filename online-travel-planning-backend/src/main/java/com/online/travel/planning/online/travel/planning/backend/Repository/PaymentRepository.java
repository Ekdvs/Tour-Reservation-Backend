package com.online.travel.planning.online.travel.planning.backend.Repository;

public interface PaymentRepository extends MongoRepository<Payment,String>{
    List<Payment> findByUserId(String userId);
    List<Payment> findByReservationId(String reservationId);


}
