package com.online.travel.planning.online.travel.planning.backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.travel.planning.online.travel.planning.backend.Model.TravelPlace;
import com.online.travel.planning.online.travel.planning.backend.Service.TravelPlaceService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/place")
public class TravelPlaceController {
    @Autowired
    private TravelPlaceService travelPlaceService;

     //add place
    @PostMapping("/addplace")
    public ResponseEntity<TravelPlace> addPlace(@RequestBody TravelPlace travelPlace) {
        TravelPlace savedPlace = travelPlaceService.createTravelPlace(travelPlace);
        return ResponseEntity.ok(savedPlace);
    }
    //get all places
    @GetMapping("/allplaces")
    public ResponseEntity<List<TravelPlace>> getAllPlaces() {
        List<TravelPlace> places = travelPlaceService.getAllTravelPlaces();
        return ResponseEntity.ok(places);
    }
     //delete place by name
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTravelPlace(@PathVariable("id") String placename) {
        travelPlaceService.deleteTravelPlace(placename);
        return ResponseEntity.ok("place with ID " + placename + " has been deleted successfully.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<TravelPlace> getPlaceById(@PathVariable String id) {
        TravelPlace place = travelPlaceService.getTravelPlaceById(id);
        return place != null ? ResponseEntity.ok(place) : ResponseEntity.notFound().build();
    }
    // Get TravelPlace by placeName
    @GetMapping("/getPlaceByName/{id}")
    public TravelPlace getPlaceByName(@PathVariable("id") String placeName) {
        return travelPlaceService.getTravelPlaceByName(placeName);
    }

    @PutMapping("/update/{name}")
    public ResponseEntity<TravelPlace> updatePlace(@PathVariable("name") String placeName, @RequestBody TravelPlace travelPlace) {
         // Ensure that the travelPlace to update has the correct values for the update
        if (travelPlace == null || placeName == null || placeName.isEmpty()) {
            return ResponseEntity.badRequest().build(); // Bad Request if input is invalid
        }
        // Call the service to update the TravelPlace
        TravelPlace updatedPlace = travelPlaceService.updateTravelPlace(placeName, travelPlace);
        // Check if the place was successfully updated or not found
        if (updatedPlace != null) {
            return ResponseEntity.ok(updatedPlace);  // Return the updated place with status 200 OK
        }
        else {
            return ResponseEntity.notFound().build();  // Return 404 if the place was not found
        }
    }

}
