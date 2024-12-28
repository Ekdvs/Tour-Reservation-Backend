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

    public Optional<Payment> getPaymentById(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    public void deletePayment(String paymentId) {
        paymentRepository.deleteById(paymentId);
    }
    public Payment processPayment(Payment payment) {
        Payment savedPayment = paymentRepository.save(payment);

        if (payment.isCheckAccept()) {
            sendPaymentSuccessEmail(payment);
        }
        return savedPayment;
    }
    public void sendPaymentSuccessEmail(Payment payment) {
        // Fetch user details by userId
        Optional<User> userOptional = userRepository.findByUserId(payment.getUserId());
        Optional<Reservation> reservationOptional = reservationRepository.findById(payment.getReservationId());







}
