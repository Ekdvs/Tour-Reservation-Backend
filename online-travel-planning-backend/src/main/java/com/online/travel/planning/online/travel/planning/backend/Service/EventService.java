package com.online.travel.planning.online.travel.planning.backend.Service;

import java.util.List;
import java.util.Optional;

import com.online.travel.planning.online.travel.planning.backend.Model.Event;

public interface EventService {
    List<Event> getAllEvents();
    Optional<Event> getEventById(String eventId);


}
