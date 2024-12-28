package com.online.travel.planning.online.travel.planning.backend.Controller;
@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentServiceImplementation paymentService;
    @Autowired
    private PaymentRepository paymentRepository;
    public PaymentController(PaymentServiceImplementation paymentService) {
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










}
