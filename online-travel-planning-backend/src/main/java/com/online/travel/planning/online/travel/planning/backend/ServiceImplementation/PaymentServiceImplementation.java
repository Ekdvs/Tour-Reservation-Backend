package com.online.travel.planning.online.travel.planning.backend.ServiceImplementation;
@Service
public class PaymentServiceImplementation implements PaymentService{
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReservationRepository reservationRepository;


}
