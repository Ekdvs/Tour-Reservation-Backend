package com.online.travel.planning.online.travel.planning.backend.Controller;

import com.online.travel.planning.online.travel.planning.backend.Model.User;
import com.online.travel.planning.online.travel.planning.backend.Repository.UserRepository;
import com.online.travel.planning.online.travel.planning.backend.Service.UserService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3001")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    // Create a new user
    @PostMapping("/addUser")
    public ResponseEntity<User> createUser(@RequestBody User user) throws MessagingException {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    // Get user by ID
    @GetMapping("/getUserById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/getUserNameByID/{id}")
    public String getUsernameById(@PathVariable("id") String userId) {
        User user = userService.getUserNameById(userId);
        String userName=user.getFirstName();
        return userName;
    }

    @GetMapping("/getUserByEmail/{id}")
    public User getUserByEmail(@PathVariable("id") String userEmail) {
        return userService.getUserByUserEmail(userEmail);
    }

    // Get all users
    @GetMapping("/allUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Delete user by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User with ID " + userId + " has been deleted successfully.");
    }

    @PostMapping("/login")
    public String login(@RequestBody User user, HttpSession session) {
        User existingUser = userRepository.findByUserEmail(user.getUserEmail());
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            session.setAttribute("user", user);
            return "Login successful";
        } else {
            return "Invalid username or password";
        }
    }
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "Logged out successfully";
    }
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        if (userRepository.findByUserEmail(user.getUserEmail()) != null) {
            return "User already registered as a user";
        }
        userRepository.save(user);
        return "User registered successfully";
    }

    @PostMapping("/sendotpcode")
    public ResponseEntity<String> sendRecoveryCode(@RequestBody Map<String, String> payload) {
        String userEmail = payload.get("userEmail");

        if (userEmail == null || userEmail.isBlank()) {
            return ResponseEntity.badRequest().body("Email cannot be empty.");
        }

        try {
            String result = userService.sendRecoveryCode(userEmail);
            return ResponseEntity.ok(result); // Return "OTP sent successfully" if service works fine.
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send OTP. Please try again.");
        }
    }


    @PostMapping("/verify-code")
    public ResponseEntity<Map<String, Object>> verifyRecoveryCode(
            @RequestParam String userEmail,
            @RequestParam String recoveryCode) {
        // Validate input
        if (userEmail == null || userEmail.isBlank() || recoveryCode == null || recoveryCode.isBlank()) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Email and recovery code cannot be empty.");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            // Verify OTP
            boolean isVerified = userService.verifyRecoveryCode(userEmail, recoveryCode);
            if (isVerified) {
                // Successful verification
                Map<String, Object> response = new HashMap<>();
                response.put("success", true);
                response.put("message", "OTP verified successfully.");
                return ResponseEntity.ok(response);
            } else {
                // Invalid OTP
                Map<String, Object> response = new HashMap<>();
                response.put("success", false);
                response.put("message", "Invalid OTP. Please try again.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        } catch (Exception e) {
            // Handle server errors
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Verification failed. Please try again.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }



    @PostMapping("/update-password/{email}")
    public ResponseEntity<String> updatePassword(
            @PathVariable("email") String userEmail,
            @RequestBody Map<String, String> payload) {

        // Extract newPassword from the request body
        String Password = payload.get("Password");

        // Validate the newPassword input
        if (Password == null || Password.isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("New password must not be empty.");
        }

        try {
            // Call the service to update the password
            User user = userService.updatePassword(userEmail, Password);

            // Check if the user was found and the password updated
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("User not found or password could not be updated.");
            }

            // Return success message
            return ResponseEntity.ok("Password updated successfully.");
        } catch (RuntimeException e) {
            // Handle any exceptions thrown by the service
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while updating the password: " + e.getMessage());
        }
    }



    @PutMapping("/{email}")
    public ResponseEntity<User> updateUserProfile(@PathVariable String email,@RequestBody User updatedUser ) {
        User user = userService.updateUserProfile(email, updatedUser);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(user);
    }

    // Get user profile by email
    @GetMapping("/{email}")
    public ResponseEntity<User> getUserProfile(@PathVariable String email) {
        User user = userService.getUserProfile(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(user);
    }}



    /*@Value("${profile-pic.upload-dir}")
    private String uploadDir; // The directory to save the profile pictures

    @PutMapping("/{email}")
    public ResponseEntity<Map<String, String>> updateUserProfile(@PathVariable String email,
                                                                 @RequestParam Map<String, String> formData,
                                                                 @RequestParam(required = false) MultipartFile profilePicture) {
        Map<String, String> response = new HashMap<>();

        try {
            if (profilePicture != null && !profilePicture.isEmpty()) {
                String fileName = StringUtils.cleanPath(profilePicture.getOriginalFilename());
                Path uploadPath = Paths.get(uploadDir);

                // Create the directory if it does not exist
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                // Save the file
                Path targetLocation = uploadPath.resolve(fileName);
                Files.copy(profilePicture.getInputStream(), targetLocation);

                // Store the image URL in the database (e.g., the file path or URL)
                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/uploads/")
                        .path(fileName)
                        .toUriString();

                // You can save the fileDownloadUri in your database (User's profile picture URL)
                response.put("profilePicture", fileDownloadUri);
            }

            // Update other user information as needed
            response.put("message", "User profile updated successfully!");
            return ResponseEntity.ok(response);

        } catch (IOException e) {
            response.put("message", "Could not upload the file. Please try again!");
            return ResponseEntity.status(500).body(response);
        }
    }*/





