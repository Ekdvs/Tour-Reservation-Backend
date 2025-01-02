package com.online.travel.planning.online.travel.planning.backend.ServiceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.travel.planning.online.travel.planning.backend.Model.Event;
import com.online.travel.planning.online.travel.planning.backend.Model.User;
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

    @Override
    public List<Event> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        List<User> allUsers = userRepository.findAll();
        for (Event event : events) {
            String imagePath = event.getEventImagePath();
            if (imagePath != null && !imagePath.isEmpty()) {
                String fullPath = getAccessibleUrl("http://localhost:8080" + imagePath);
                event.setEventImagePath(fullPath);
            }
           

        }

        return events;
    }

    @Override
    public Optional<Event> getEventById(String eventId) {
        return eventRepository.findById(eventId);
    }

    public List<Event> getEventByType(String eventType) {
        List<Event> events = eventRepository.findByType(eventType);
        for (Event event : events) {
            String imagePath = event.getEventImagePath();

            if (imagePath != null && !imagePath.isEmpty()) {
                String fullPath = getAccessibleUrl("http://localhost:8080" + imagePath);
                event.setEventImagePath(fullPath);
            }
        }
        return events;
    }

    private String getAccessibleUrl(String... urls) {
        for (String url : urls) {
            if (isUrlAccessible(url)) {
                return url;
            }
        }
        return null; // or handle it if neither URL is accessible
    }

    private boolean isUrlAccessible(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            return (responseCode == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            return false;
        }
    }



}
