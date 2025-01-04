package com.online.travel.planning.online.travel.planning.backend.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.online.travel.planning.online.travel.planning.backend.Model.TravelPlace;

public interface TravelPlaceRepository extends MongoRepository <TravelPlace,String> {
    //Optional<TravelPlace> findByTravelPlaceName(String travelPlacename);
   // TravelPlace findByTravelPlaceName(String travelPlaceName);
   // Optional<TravelPlace>findTravelPlaceByUserId(String userId);
   TravelPlace findByPlaceName(String PlaceName);


}
