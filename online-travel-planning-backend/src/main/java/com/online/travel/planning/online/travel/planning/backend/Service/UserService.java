package com.online.travel.planning.online.travel.planning.backend.Service;

import com.online.travel.planning.online.travel.planning.backend.Model.User;

import jakarta.mail.MessagingException;

public interface UserService {
    User createUser(User user) throws MessagingException;
    User getUserById(String userId);
    User getUserNameById(String userId);

}
