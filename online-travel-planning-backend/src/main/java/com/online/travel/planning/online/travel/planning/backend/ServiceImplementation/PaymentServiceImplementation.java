package com.online.travel.planning.online.travel.planning.backend.ServiceImplementation;
import com.online.travel.planning.online.travel.planning.backend.Model.Packages;
import com.online.travel.planning.online.travel.planning.backend.Model.Payment;
import com.online.travel.planning.online.travel.planning.backend.Model.Reservation;
import com.online.travel.planning.online.travel.planning.backend.Model.User;
import com.online.travel.planning.online.travel.planning.backend.Repository.PackagesRepository;
import com.online.travel.planning.online.travel.planning.backend.Repository.PaymentRepository;
import com.online.travel.planning.online.travel.planning.backend.Repository.ReservationRepository;
import com.online.travel.planning.online.travel.planning.backend.Repository.UserRepository;
import com.online.travel.planning.online.travel.planning.backend.Service.Email_Service;
import com.online.travel.planning.online.travel.planning.backend.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImplementation implements PaymentService{
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Email_Service emailService;
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
        if (userOptional.isPresent() && reservationOptional.isPresent()) {
            User user = userOptional.get();
            Reservation reservation = reservationOptional.get();
            Optional<Packages> optionalPackages = packagesRepository.findById(reservation.getPackgeId());
            Packages packages = optionalPackages.get();
            try {
                String userEmail = user.getUserEmail();
                String subject = "Payment Confirmation";
                String message = "<html>" +
                        "<head>" +
                        "<style>" +
                        "body { font-family: Arial, sans-serif; margin: 0; padding: 20px; background-color: #f4f4f4; }" +
                        ".container { max-width: 800px; margin: auto; background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 4px 10px rgba(0, 0, 0, 0.15); }" +
                        ".header { background-color: #135bf2; color: white; padding: 15px; border-radius: 8px 8px 0 0; text-align: center; }" +
                        ".content { padding: 20px; font-size: 16px; color: #333; }" +
                        ".content p { line-height: 1.6; }" +
                        ".amount { font-weight: bold; color: #135bf2; }" +
                        ".table { width: 100%; border-collapse: collapse; margin-top: 20px; }" +
                        ".table td { padding: 8px; border: 1px solid #dddddd; text-align: left; }" +
                        ".footer { margin-top: 20px; padding-top: 15px; border-top: 1px solid #dddddd; text-align: center; font-size: 13px; color: #777; }" +
                        ".footer p { margin: 5px 0; }" +
                        "</style>" +
                        "</head>" +
                        "<body>" +
                        "<div class='container'>" +
                        "<div class='header'><h2>Payment Confirmation " + " for " + reservation.getPackgeId() + "</h2></div>" +
                        "<div class='content'>" +
                        "<p>Dear " + user.getFirstName() + " " + user.getLastName() + ",</p>" +
                        "<p>Thank you for choosing BkTicketing for your reservation. Below are your payment details:</p>" +

                        "<table class='table'>" +
                        "<tr><td><strong>Email Address:</strong></td><td>" + user.getUserEmail() + "</td></tr>" +
                        "<tr><td><strong>Event Name:</strong></td><td>" + packages.getPackageName() + "</td></tr>" +
                        "<tr><td><strong>Reservation Date:</strong></td><td>" + user.getDateRegistered() + "</td></tr>" +
                        "<tr><td><strong>Reservation Time:</strong></td><td>" + user.getTimeRegistered() + "</td></tr>" +
                        "<tr><td><strong>Event Date:</strong></td><td>" + packages.getDescription() + "</td></tr>" +
                        "<tr><td><strong>Event Time:</strong></td><td>" + packages.getPackageName() + "</td></tr>" +
                        "<tr><td><strong>Event Venue:</strong></td><td class='amount'>" + packages.getLocation() + "</td></tr>" +
                        "<tr><td><strong>Number of Tickets:</strong></td><td>" + reservation.getNumOfPerson() + "</td></tr>" +
                        "<tr><td><strong>Amount Paid:</strong></td><td class='amount'>Rs" + payment.getAmount() + ".0</td></tr>" +
                        "<tr><td><strong>Transaction Description:</strong></td><td>" + payment.getDescription() + "</td></tr>" +
                        "</table>" +

                        "<p>We are thrilled to have you on board. Enjoy the event!</p>" +
                        "<p>Warm regards, <strong class='amount'>BkTicketing<sup>LK</sup></strong></p>" +
                        "</div>" +

                        "<div class='footer'>" +
                        "<p>&copy; 2024 BkTicketing LK. All rights reserved.</p>" +
                        "<p>If you have any questions, please contact us at support@bkticketing.lk</p>" +
                        "</div>" +
                        "</div>" +
                        "</body>" +
                        "</html>";

                emailService.sendEmail(userEmail, subject, message); // Ensure this method supports HTML content
                System.out.println("Email sent successfully to: " + userEmail);

            } catch (Exception e) {
                System.out.println("Failed to send email: " + e.getMessage());
            }
        } else {
            System.out.println("User not found with ID: " + payment.getUserId());
            }
    }

}
