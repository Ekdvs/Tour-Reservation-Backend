package com.online.travel.planning.online.travel.planning.backend.Controller;
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
 
 


}
