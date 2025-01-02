package com.online.travel.planning.online.travel.planning.backend.ServiceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.travel.planning.online.travel.planning.backend.Repository.EventRepository;
import com.online.travel.planning.online.travel.planning.backend.Repository.UserRepository;
import com.online.travel.planning.online.travel.planning.backend.Service.Email_Service;
import com.online.travel.planning.online.travel.planning.backend.Service.EventService;

@Service
public class EventServiceImplementation implements EventService{

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Email_Service emailService;
}
