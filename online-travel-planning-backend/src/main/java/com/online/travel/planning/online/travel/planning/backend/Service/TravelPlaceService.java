package com.online.travel.planning.online.travel.planning.backend.Service;
import com.online.travel.planning.online.travel.planning.backend.Model.TravelPlace;
import java.util.List;


public interface TravelPlaceService {
    TravelPlace createTravelPlace(TravelPlace travelPlace);
    TravelPlace getTravelPlaceById(String placeId);
    TravelPlace getTravelPlaceByName(String placeName);
    List<TravelPlace> getAllTravelPlaces();
    void deleteTravelPlace(String placeName);
    TravelPlace updateTravelPlace(String placeName, TravelPlace travelPlace);
}
