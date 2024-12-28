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




}
