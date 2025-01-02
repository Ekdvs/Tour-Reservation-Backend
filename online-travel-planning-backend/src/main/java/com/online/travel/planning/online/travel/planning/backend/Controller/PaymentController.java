package com.online.travel.planning.online.travel.planning.backend.Controller;
import com.online.travel.planning.online.travel.planning.backend.Model.DailyIncome;
import com.online.travel.planning.online.travel.planning.backend.Model.Payment;
import com.online.travel.planning.online.travel.planning.backend.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private com.online.travel.planning.online.travel.planning.backend.ServiceImplementation.PaymentServiceImplementation paymentService;
    @Autowired
    private PaymentRepository paymentRepository;
    public PaymentController(com.online.travel.planning.online.travel.planning.backend.ServiceImplementation.PaymentServiceImplementation paymentService) {
        this.paymentService = paymentService;
    }
    @GetMapping("/dailyIncome")
    public ResponseEntity<List<DailyIncome>> getDailyIncome() {
        List<DailyIncome> dailyIncome = paymentRepository.aggregateDailyIncome(); // Implement aggregation in your repository
        return ResponseEntity.ok(dailyIncome);
    }
    @PostMapping("/process") // add method
    public Payment processPayment(@RequestBody Payment payment) {
        return paymentService.processPayment(payment);
    }
    @GetMapping("/getPaymentByUserId/{id}")
    public List<Payment> getPaymentByUserId(@PathVariable("id") String userId) {
        return paymentService.getPaymentByUserId(userId);
    }
    @GetMapping("/getPaymentByReservationId/{id}")
    public List<Payment> getPaymentByReservationId(@PathVariable("id") String reservationId) {
        return paymentService.getPaymentByReservationId(reservationId);
    }
    @GetMapping("/getAllPayment")
    public List<Payment> getAllPayment() {
        return paymentService.getAllPayment();
    }
    @GetMapping("/getPaymentById/{id}")
    public Optional<Payment> getPaymentById(@PathVariable("id") String paymentId) {
        return paymentService.getPaymentById(paymentId);
    }
    @DeleteMapping("/deletePayment/{id}")
    public String deletePayment(@PathVariable("id") String paymentId) {
        paymentService.deletePayment(paymentId);
        return "Payment deleted with id " + paymentId;
    }

}
