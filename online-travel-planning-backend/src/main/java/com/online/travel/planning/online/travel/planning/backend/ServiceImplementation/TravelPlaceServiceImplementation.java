package com.online.travel.planning.online.travel.planning.backend.ServiceImplementation;

@Service
public class TravelPlaceServiceImplementation implements TravelPlaceService{
    @Autowired
    private TravelPlaceRepository travelPlaceRepository;

    @Autowired
    private TravelPlaceService travelPlaceService;

    @Autowired
    private UserRepository userRepository;





}
