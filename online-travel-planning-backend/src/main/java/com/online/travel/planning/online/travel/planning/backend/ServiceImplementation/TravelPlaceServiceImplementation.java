package com.online.travel.planning.online.travel.planning.backend.ServiceImplementation;
import com.online.travel.planning.online.travel.planning.backend.Model.TravelPlace;
import com.online.travel.planning.online.travel.planning.backend.Model.User;
import com.online.travel.planning.online.travel.planning.backend.Repository.TravelPlaceRepository;
import com.online.travel.planning.online.travel.planning.backend.Repository.UserRepository;
import com.online.travel.planning.online.travel.planning.backend.Service.Email_Service;
import com.online.travel.planning.online.travel.planning.backend.Service.TravelPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Optional;

@Service
public class TravelPlaceServiceImplementation implements TravelPlaceService{
    @Autowired
    private TravelPlaceRepository travelPlaceRepository;

    @Autowired
    private TravelPlaceService travelPlaceService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Email_Service emailService;

    @Override
    public TravelPlace createTravelPlace(TravelPlace travelPlace) {
        TravelPlace newplace= travelPlaceRepository.save(travelPlace);
        List<User> allUsers = userRepository.findAll();

        // Prepare the email content
        String subject = "New Travel Place Added: " + travelPlace.getPlaceName();
        String message =
                "<html>" +
                        "<head>" +
                        "<style>" +
                        "body { font-family: Arial, sans-serif; margin: 0; padding: 20px; background-color: #f4f4f4; }" +
                        ".container { max-width: 800px; margin: auto; background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 4px 10px rgba(0, 0, 0, 0.15); }" +
                        ".header { background-color: #135bf2; color: white; padding: 15px; border-radius: 8px 8px 0 0; text-align: center; }" +
                        ".content { padding: 20px; font-size: 16px; color: #333; }" +
                        ".content p { line-height: 1.6; }" +
                        ".footer { margin-top: 20px; padding-top: 15px; border-top: 1px solid #dddddd; text-align: center; font-size: 13px; color: #777; }" +
                        ".footer p { margin: 5px 0; }" +
                        "</style>" +
                        "</head>" +
                        "<body>" +
                        "<div class='container'>" +
                        "<div class='header'>" +
                        "<h1 style='color: white;'>Exciting New Travel Place!</h1>" +
                        "</div>" +
                        "<div class='content'>" +
                        "<p>Hello,</p>" +
                        "<p>We are thrilled to announce the addition of a new travel destination:</p>" +
                        "<p><strong>" + travelPlace.getPlaceName() + "</strong></p>" +
                        "<p>Location: " + travelPlace.getLocation() + "</p>" +
                        "<p>Description: " + travelPlace.getDescription() + "</p>" +
                        "<p>Price: $" + travelPlace.getPrice() + "</p>" +
                        "<br>" +
                        "<p>Plan your next adventure now!</p>" +
                        "<p><strong>Travel Planning Team</strong></p>" +
                        "</div>" +
                        "<div class='footer'>" +
                        "<p>&copy; 2024 online-travel-planning LK. All rights reserved.</p>" +
                        "<p>Contact us at ceylontravelplanning@gmail.com</p>" +
                        "</div>" +
                        "</div>" +
                        "</body>" +
                        "</html>";
        allUsers.forEach(user -> {
            if (user.getUserRole().equals("user") || user.getUserRole().equals("GUIDE")) {
                emailService.sendEmail(user.getUserEmail(), subject, message);
            }
        });


        return newplace;
    }

    @Override
    public List<TravelPlace> getAllTravelPlaces() {
        List<TravelPlace> places = travelPlaceRepository.findAll();
        for (TravelPlace place : places) {
            String imagePath=place.getPlaceImagePath();
            if (imagePath != null && !imagePath.isEmpty()) {
                String fullPath = getAccessibleUrl("http://localhost:8080" + imagePath);
                place.setPlaceImagePath(fullPath);
            }
        }
        return places;
    }
    private String getAccessibleUrl(String... urls) {
        for (String url : urls) {
            if (isUrlAccessible(url)) {
                return url;
            }
        }
        return null; // or handle it if neither URL is accessible
    }
    private boolean isUrlAccessible(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            return (responseCode == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public void deleteTravelPlace(String placeId) {
        travelPlaceRepository.deleteById(placeId);
    }
    @Override
    public Optional<TravelPlace> getTravelPlaceById(String placeId) {
    return travelPlaceRepository.findById(placeId);
    }
   

}
