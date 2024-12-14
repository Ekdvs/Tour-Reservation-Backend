package com.online.travel.planning.online.travel.planning.backend.Service;

import java.util.List;

import com.online.travel.planning.online.travel.planning.backend.Model.User;

import jakarta.mail.MessagingException;

public interface UserService {
    User createUser(User user) throws MessagingException;
    User getUserById(String userId);
    User getUserNameById(String userId);
    User getUserByUserEmail(String userEmail);
List<User> getAllUsers();
void deleteUser(String userId);
String sendRecoveryCode(String userEmail);

}
