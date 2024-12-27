package com.online.travel.planning.online.travel.planning.backend.ServiceImplementation;


import com.online.travel.planning.online.travel.planning.backend.Model.User;
import com.online.travel.planning.online.travel.planning.backend.Repository.UserRepository;
import com.online.travel.planning.online.travel.planning.backend.Service.Email_Service;
import com.online.travel.planning.online.travel.planning.backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Email_Service emailService;

    private final Map<String,String> recoveryCodes = new HashMap<>();

    public User createUser(User user) {

        if (user == null || user.getUserEmail() == null || user.getUserEmail().isEmpty()) {
            throw new IllegalArgumentException("Invalid user data. Email is required.");
        }


        User newUser = userRepository.save(user);


        String userEmail = newUser.getUserEmail();


        String subject = "Welcome to Travel Planning Website!";
        String message = "<html>" +
                "<head>" +
                "<style>" +
                "body { font-family: Arial, sans-serif; margin: 0; padding: 20px; background-color: #f4f4f4; }" +
                ".container { max-width: 800px; margin: auto; background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 4px 10px rgba(0, 0, 0, 0.15); }" +
                ".header { background-color: #4CAF50; color: white; padding: 15px; border-radius: 8px 8px 0 0; text-align: center; }" +
                ".content { padding: 20px; font-size: 16px; color: #333; }" +
                ".content p { line-height: 1.6; }" +
                ".welcome { font-weight: bold; color: #135bf2; font-size: 18px; }" +
                ".footer { margin-top: 20px; padding-top: 15px; border-top: 1px solid #dddddd; text-align: center; font-size: 13px; color: #777; }" +
                ".footer p { margin: 5px 0; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class='container'>" +
                "<div class='header'>" +
                "<h1>Welcome to Travel Planning Website!</h1>" +
                "</div>" +
                "<div class='content'>" +
                "<p>Dear <strong>" + newUser.getFirstName() + " " + newUser.getLastName() + "</strong>,</p>" +
                "<p>Thank you for registering with us. We are excited to have you on board.</p>" +
                "<p>Enjoy planning your trips and feel free to explore our travel services!</p>" +
                "<br>" +
                "<p>Best Regards,</p>" +
                "<p><strong>Travel Planning Team</strong></p>" +
                "</div>" +
                "<div class='footer'>" +
                "<p>&copy; 2024 online-travel-planning LK. All rights reserved.</p>" +
                "<p>If you have any questions, please contact us at <a href='mailto:ceylontravelplanning@gmail.com'>ceylontravelplanning@gmail.com</a></p>" +
                "</div>" +
                "</div>" +
                "</body>" +
                "</html>";

        // Send the welcome email
        try {
            emailService.sendEmail(userEmail, subject, message); // Ensure this method supports HTML content
            System.out.println("Email sent successfully to: " + userEmail);
        } catch (Exception e) {
            System.err.println("Failed to send email to: " + userEmail);
            e.printStackTrace();
        }

        return newUser;
    }


    @Override
    public User getUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
    }
    @Override
    public User getUserNameById(String userId) {
        return userRepository.findUsernameByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
    }
    @Override
    public User getUserByUserEmail(String userEmail) {
        return userRepository.findByUserEmail(userEmail);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public String sendRecoveryCode(String userEmail) {
        // Find user by email
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByUserEmail(userEmail));
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("No user found with email: " + userEmail);
        }


        String recoveryCode = String.format("%06d", new Random().nextInt(999999));


        recoveryCodes.put(userEmail, recoveryCode);


        // Prepare the email content
        String subject = "Password Recovery Code!";
        String message =
                "<html>" +
                        "<head>" +
                        "<style>" +
                        "body { font-family: Arial, sans-serif; margin: 0; padding: 20px; background-color: #f4f4f4; }" +
                        ".image { width: 50px; height: 50px; display: block; margin: 0 auto; }" +
                        ".container { max-width: 800px; margin: auto; background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 4px 10px rgba(0, 0, 0, 0.15); }" +
                        ".header { background-color: #00ff99; color: white; padding: 15px; border-radius: 8px 8px 0 0; text-align: center; }" +
                        ".content { padding: 20px; font-size: 16px; color: #333; }" +
                        ".content p { line-height: 1.6; }" +
                        ".recovery-code { font-weight: bold; color: #135bf2; font-size: 18px; }" +
                        ".footer { margin-top: 20px; padding-top: 15px; border-top: 1px solid #dddddd; text-align: center; font-size: 13px; color: #777; }" +
                        ".footer p { margin: 5px 0; }" +
                        "</style>" +
                        "</head>" +
                        "<body>" +
                        "<div class='container'>" +
                        "<div class='header'><h2>Account Recovery</h2></div>" +
                        "<div class='content'>" +
                        "<p>Dear " + optionalUser.get().getFirstName() + " " + optionalUser.get().getLastName() + ",</p>" +
                        "<p>We received a request to reset the password for your account. Use the code below to proceed with password recovery:</p>" +
                        "<p class='recovery-code'>" + recoveryCode + "</p>" +
                        "<p>If you did not request this change, please contact our support team immediately.</p>" +
                        "<p>Warm regards,</p>" +
                        "<p><strong>online-travel-planning Support Team</strong></p>" +
                        "</div>" +
                        "<div class='footer'>" +
                        "<p>&copy; 2024 online-travel-planning LK. All rights reserved.</p>" +
                        "<p>If you have any questions, please contact us at ceylontravelplanning@gmail.com</p>" +
                        "</div>" +
                        "</div>" +
                        "</body>" +
                        "</html>";




        // Send the welcome email
        emailService.sendOTPEmail(userEmail, subject, message);


        return recoveryCode; // Optionally return it to the frontend for testing purposes
    }


    @Override
    public boolean verifyRecoveryCode(String userEmail, String recoveryCode) {
        String storedCode = recoveryCodes.get(userEmail);
        if (storedCode != null && storedCode.equals(recoveryCode)) {
            recoveryCodes.remove(userEmail);
            return true;
        }
        return false;
    }

    @Override
    public User updatePassword(String userEmail, String newPassword) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByUserEmail(userEmail));
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("No user found with email: " + userEmail);
        }

        User user = optionalUser.get();
        user.setPassword(newPassword); // Update password
        return userRepository.save(user);
    }

    @Override
    public User getUserProfile(String email) {
        return userRepository.findByUserEmail(email);
    }

    @Override
    public User updateUserProfile(String userEmail, User user) {
        User updatedUser = userRepository.findByUserEmail(userEmail);
        if (updatedUser == null) {
            return null;
        }
        else {
            updatedUser.setFirstName(user.getFirstName());
            updatedUser.setLastName(user.getLastName());
            updatedUser.setPhoneNumber(user.getPhoneNumber());
            updatedUser.setTitle(user.getTitle());
            updatedUser.setGender(user.getGender());
            updatedUser.setCountry(user.getCountry());
            return userRepository.save(updatedUser);

        }
    }




}
