package com.online.travel.planning.online.travel.planning.backend.ServiceImplementation;

import com.online.travel.planning.online.travel.planning.backend.Repository.UserRepository;
import com.online.travel.planning.online.travel.planning.backend.Service.UserService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepository userRepository;

   /*  @Autowired
    private EmailService emailService;*/

    private final Map<String,String> recoveryCodes = new HashMap<>();

    public User createUser(User user) {
        // Save the user to the database
        User newUser = userRepository.save(user);

        // Get the email from the User object
        String userEmail = newUser.getUserEmail();

        // Prepare the email content
        String subject = "Welcome to Travel Planning Website!";
        String message =
                "<html>" +
                        "<head>" +
                        "<style>" +
                        "body { font-family: Arial, sans-serif; margin: 0; padding: 20px; background-color: #f4f4f4; }" +
                        ".container { max-width: 800px; margin: auto; background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 4px 10px rgba(0, 0, 0, 0.15); }" +
                        ".header { background-color: yellow; color: white; padding: 15px; border-radius: 8px 8px 0 0; text-align: center; }" +
                        ".content { padding: 20px; font-size: 16px; color: #333; }" +
                        ".content p { line-height: 1.6; }" +
                        ".welcome { font-weight: bold; color: #135bf2; font-size: 18px; }" +
                        ".footer { margin-top: 20px; padding-top: 15px; border-top: 1px solid #dddddd; text-align: center; font-size: 13px; color: #777; }" +
                        ".footer p { margin: 5px 0; }" +
                        "</style>" +
                        "</head>" +
                        "<body>" +
                        "<h1 style='color: #4CAF50;'>Welcome to Travel Planning Website!</h1>" +
                        "<p>Dear <strong>" + newUser.getFirstName() + " " + newUser.getLastName() + "</strong>,</p>" +
                        "<p>Thank you for registering with us. We are excited to have you on board.</p>" +
                        "<p>Enjoy planning your trips!</p>" +
                        "<br>" +
                        "<p>Best Regards,</p>" +
                        "<p><strong>Travel Planning Team</strong></p>" +
                        "<div class='footer'>" +
                        "<p>&copy; 2024 online-travel-planning LK. All rights reserved.</p>" +
                        "<p>If you have any questions, please contact us at ceylontravelplanning@gmail.com</p>" +
                        "</div>"+
                        "</body>" +
                        "</html>";



        // Send the welcome email
        emailService.sendWelcomeEmail(userEmail, subject, message);

        return newUser;
    }
}
