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









}
