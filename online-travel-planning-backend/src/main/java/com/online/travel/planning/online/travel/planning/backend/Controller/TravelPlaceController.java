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






}
