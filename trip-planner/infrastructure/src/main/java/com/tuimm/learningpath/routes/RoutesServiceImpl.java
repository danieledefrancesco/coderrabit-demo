package com.tuimm.learningpath.routes;

import com.tuimm.learningpath.places.Place;
import com.tuimm.learningpath.vehicles.DrivingProfile;
import com.tuimm.learningpath.HttpClientUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.util.Formatter;
import java.util.Locale;

@Service
public class RoutesServiceImpl implements RoutesService {
    private final HttpClient httpClient;
    private final String baseUri;
    private final String apiKey;

    public RoutesServiceImpl(HttpClient httpClient,
                             @Value("${routesService.baseUri}") String baseUri,
                             @Value("${routesService.apiKey}") String apiKey) {
        this.httpClient = httpClient;
        this.baseUri = baseUri;
        this.apiKey = apiKey;
    }

    @Override
    public Route getRoute(Place from, Place to, DrivingProfile drivingProfile) {
        String profile = toProfileString(drivingProfile);

        URI uri = createUri(from, to, profile);

        DirectionsResponse directionsResponse = HttpClientUtils.executeGet(httpClient, uri, DirectionsResponse.class);
        return createRoute(from, to, drivingProfile, directionsResponse);
    }

    private static Route createRoute(Place from, Place to, DrivingProfile drivingProfile, DirectionsResponse directionsResponse) {
        SegmentResponse segmentResponse = directionsResponse.getFeatures().get(0).getProperties().getSegments().get(0);
        return Route.builder()
                .from(from)
                .to(to)
                .drivingProfile(drivingProfile)
                .distanceInKilometers(segmentResponse.getDistance() / 1000)
                .build();
    }

    private URI createUri(Place from, Place to, String profile) {
        try (Formatter formatter = new Formatter(Locale.US)) {
            return URI.create(formatter.format("%s/directions/%s?api_key=%s&start=%f,%f&end=%f,%f",
                    baseUri,
                    profile,
                    apiKey,
                    from.getGeoCoordinate().getLongitude(),
                    from.getGeoCoordinate().getLatitude(),
                    to.getGeoCoordinate().getLongitude(),
                    to.getGeoCoordinate().getLatitude()).toString());
        }
    }

    private static String toProfileString(DrivingProfile drivingProfile) {
        return switch (drivingProfile) {
            case CAR_PROFILE -> "driving-car";
            case HGV_PROFILE -> "driving-hgv";
            default -> "cycling-regular";
        };
    }
}