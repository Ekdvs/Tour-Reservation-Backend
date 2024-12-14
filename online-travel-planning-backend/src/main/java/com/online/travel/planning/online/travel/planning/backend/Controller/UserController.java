package com.online.travel.planning.online.travel.planning.backend.Controller;

import com.online.travel.planning.online.travel.planning.backend.Model.User;
import com.online.travel.planning.online.travel.planning.backend.Repository.UserRepository;
import com.online.travel.planning.online.travel.planning.backend.Service.UserService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RestController
@CrossOrigin("http://localhost:3000")
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
    public String sendRecoveryCode(@RequestBody Map<String, String> payload) {
        String userEmail = payload.get("userEmail");
        if (userEmail == null || userEmail.isBlank()) {
            throw new RuntimeException("Email cannot be empty.");
        }
        return userService.sendRecoveryCode(userEmail);
    }

    @PostMapping("/verify-code")
    public boolean verifyRecoveryCode(@RequestParam String userEmail, @RequestParam String recoveryCode) {
        return userService.verifyRecoveryCode(userEmail, recoveryCode);
    }

    @PostMapping("/update-password")
    public User updatePassword(@RequestParam String userEmail, @RequestParam String newPassword) {
        return userService.updatePassword(userEmail, newPassword);
    }

    // Get user profile by email
    @GetMapping("/{email}")
    public ResponseEntity<User> getUserProfile(@PathVariable String email) {
        User user = userService.getUserProfile(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{email}")
    public ResponseEntity<User> updateUserProfile(@PathVariable String email,@RequestBody User updatedUser ) {
        User user = userService.updateUserProfile(email, updatedUser);
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





