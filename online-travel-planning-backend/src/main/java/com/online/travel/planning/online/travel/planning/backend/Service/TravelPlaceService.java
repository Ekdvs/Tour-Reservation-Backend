package com.online.travel.planning.online.travel.planning.backend.Service;
import com.online.travel.planning.online.travel.planning.backend.Model.TravelPlace;
import java.util.List;
import java.util.Optional;


public interface TravelPlaceService {
    List<TravelPlace> getAllTravelPlaces();
    Optional<TravelPlace> getTravelPlaceById(String placeId);
    List<TravelPlace> searchTravelPlaceByName(String placeName);
}
