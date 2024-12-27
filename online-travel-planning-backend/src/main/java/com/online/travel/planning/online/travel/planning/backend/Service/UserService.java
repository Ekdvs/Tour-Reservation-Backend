package com.online.travel.planning.online.travel.planning.backend.Service;

import java.util.List;

import com.online.travel.planning.online.travel.planning.backend.Model.User;

import jakarta.mail.MessagingException;

public interface UserService {
    User createUser(User user) ;
    User getUserById(String userId);
    User getUserNameById(String userId);
    User getUserByUserEmail(String userEmail);
    List<User> getAllUsers();
    void deleteUser(String userId);
    String sendRecoveryCode(String userEmail);
    boolean verifyRecoveryCode(String userEmail, String recoveryCode);
    User updatePassword(String userEmail, String newPassword);
    User getUserProfile(String email);
    User updateUserProfile(String email, User updatedUser);
    long getOnlineUsersCount();
    User promoteUserToGuide(String userId);
    //List<User> getNewCustomers();
}