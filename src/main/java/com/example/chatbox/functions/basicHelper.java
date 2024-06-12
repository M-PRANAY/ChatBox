package com.example.chatbox.functions;

import com.example.chatbox.domain.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

@Component
public class basicHelper {

    @Autowired
    private static Location location;

    private static final int EARTH_RADIUS = 6371000; // Earth radius in meters

    public double calculateDistance(Location loc1, Location loc2) {
        double dLat = Math.toRadians(loc2.getLatitude() - loc1.getLatitude());
        double dLon = Math.toRadians(loc2.getLongitude() - loc1.getLongitude());

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(loc1.getLatitude())) * Math.cos(Math.toRadians(loc2.getLatitude())) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }

    private String getPublicIPAddress() throws IOException {
        URL url = new URL("https://checkip.amazonaws.com");
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        return reader.readLine().trim();
    }

    private String getLocationFromIPAddress(String ipAddress) throws IOException {
        URL url = new URL("https://ipinfo.io/" + ipAddress + "/json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder jsonBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBuilder.append(line);
        }
        return jsonBuilder.toString();
    }
}
