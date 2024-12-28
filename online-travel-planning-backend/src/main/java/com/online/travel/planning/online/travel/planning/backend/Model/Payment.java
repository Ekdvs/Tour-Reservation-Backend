package com.online.travel.planning.online.travel.planning.backend.Model;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "payment")
public class Payment {
    @Id
    private String paymentId;
    private String reservationId;
    private String userId;
    private String travelId;
    private Double amount;
    private String userEmail;
    private LocalDate paymentDate=LocalDate.now();
    private LocalTime paymentTime=LocalTime.now();
    private String description="Payment Conformance" ;
    private String transactionId;
    private boolean checkAccept=true;
    private String refundStatus = "Not Applicable";

    // add getter and setter
    public String getTransactionId() {
        return transactionId;
    }





}
