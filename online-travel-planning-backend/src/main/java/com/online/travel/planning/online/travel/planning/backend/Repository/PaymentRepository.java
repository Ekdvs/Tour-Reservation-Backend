package com.online.travel.planning.online.travel.planning.backend.Repository;

public interface PaymentRepository extends MongoRepository<Payment,String>{
    List<Payment> findByUserId(String userId);
    List<Payment> findByReservationId(String reservationId);
    @Aggregation(pipeline = {
        "{ '$group': { '_id': '$paymentDate', 'totalIncome': { '$sum': '$amount' } } }",
        "{ '$project': { 'date': '$_id', 'totalIncome': 1, '_id': 0 } }"
})
List<DailyIncome> aggregateDailyIncome();

}
