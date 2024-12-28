package com.online.travel.planning.online.travel.planning.backend.ServiceImplementation;
@Service
public class PaymentServiceImplementation implements PaymentService{
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private PackagesRepository packagesRepository;

    public List<Payment> getAllPayment() {
        return paymentRepository.findAll();
    }
    public List<Payment> getPaymentByUserId(String userId) {
        return paymentRepository.findByUserId(userId);
    }
    public List<Payment> getPaymentByReservationId(String reservationId) {
        return paymentRepository.findByReservationId(reservationId);
    }
    


}
