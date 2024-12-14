package com.online.travel.planning.online.travel.planning.backend.ServiceImplementation;

import com.online.travel.planning.online.travel.planning.backend.Repository.UserRepository;
import com.online.travel.planning.online.travel.planning.backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepository userRepository;

}
