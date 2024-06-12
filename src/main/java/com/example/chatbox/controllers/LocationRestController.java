package com.example.chatbox.controllers;

import com.example.chatbox.domain.Location;
import com.example.chatbox.functions.basicHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LocationRestController {

    @Autowired
    private Location location;

    private static final Logger log = LoggerFactory.getLogger(LocationRestController.class);
    @Autowired
    private com.example.chatbox.functions.basicHelper basicHelper;

    @PostMapping("/location")
    public String receiveLocation(@RequestBody Map<String, Object> locationData) {
        double latitude = (double) locationData.get("latitude");
        double longitude = (double) locationData.get("longitude");
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        System.out.println(location);
        log.debug("Location Recieved ->" + location);
        basicHelper bH = new basicHelper();
        Location loc2 = new Location(17.683185, 83.013798);
        System.out.println(bH.calculateDistance(location, loc2));
        return "Location received";
    }
}
