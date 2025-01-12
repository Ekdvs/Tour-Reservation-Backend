package com.online.travel.planning.online.travel.planning.backend.Controller;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.online.travel.planning.online.travel.planning.backend.Model.TravelPlace;
import com.online.travel.planning.online.travel.planning.backend.Service.TravelPlaceService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/place")
public class TravelPlaceController {
    @Autowired
    private TravelPlaceService travelPlaceService;

    private static final String IMAGE_DIRECTORY = "src/main/resources/static/images/";
     //add place
    @PostMapping("/addplace")
    public ResponseEntity<?> addEvent(@RequestPart("place")String placeJson, @RequestPart("imageFile") MultipartFile imagefile) throws IOException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            TravelPlace place = objectMapper.readValue(placeJson, TravelPlace.class);
            TravelPlace placeExist = travelPlaceService.addPlace(place,imagefile);
            return new ResponseEntity<>(placeExist, HttpStatus.CREATED);


        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //get all places
    @GetMapping("/allplaces")
    public ResponseEntity<List<TravelPlace>> getAllPlaces() {
        List<TravelPlace> places = travelPlaceService.getAllTravelPlaces();
        return ResponseEntity.ok(places);
    }

     //delete place by name
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTravelPlace(@PathVariable("id") String placeid) {

        travelPlaceService.deletePlace(placeid);
        return new ResponseEntity<>("Place deleted", HttpStatus.OK);
    }





    @PutMapping("/updatePlace/{id}")
    public ResponseEntity<?> updateEvent(
            @PathVariable("id") String placeId,
            @RequestPart("place") String placeJson,
            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile
    ) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            TravelPlace updatedPlace = objectMapper.readValue(placeJson, TravelPlace.class);
            TravelPlace place = travelPlaceService.updatePlace(placeId, updatedPlace, imageFile);
            return new ResponseEntity<>(place, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
