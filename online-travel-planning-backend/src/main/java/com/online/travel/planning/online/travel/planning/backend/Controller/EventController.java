package com.online.travel.planning.online.travel.planning.backend.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.online.travel.planning.online.travel.planning.backend.Model.Event;
import com.online.travel.planning.online.travel.planning.backend.Service.EventService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/event")
public class EventController {
    @Autowired
    private EventService eventService;

    private static final String IMAGE_DIRECTORY = "src/main/resources/static/images/";

    @GetMapping("/getAllEvents")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/searchEvents")
    public ResponseEntity<List<Event>> searchEvents(@RequestParam String name) {
        List<Event> events = eventService.searchEventsByName(name);
        return ResponseEntity.ok(events);
    }
    
    @GetMapping("/getEventById/{id}")
    public Optional<Event> getEventById(@PathVariable("id") String eventId) {
        return eventService.getEventById(eventId);
    }

    @GetMapping("/getEventByType/{eventType}")
    public List<Event> getEventByType(@PathVariable("eventType") String eventType) {
        return eventService.getEventByType(eventType);
    }



}
