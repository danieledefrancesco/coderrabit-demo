package com.tuimm.leaarningpath.infrastructure.routes;

import com.tuimm.leaarningpath.domain.places.Place;
import com.tuimm.leaarningpath.domain.routes.Route;
import com.tuimm.leaarningpath.domain.routes.RoutesService;
import com.tuimm.leaarningpath.domain.vehicles.DrivingProfile;
import com.tuimm.leaarningpath.infrastructure.HttpClientUtils;
import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.net.http.HttpClient;
import java.util.Formatter;
import java.util.Locale;

@RequiredArgsConstructor
public class RoutesServiceImpl implements RoutesService {
    private final HttpClient httpClient;
    private final String baseUri;
    private final String apiKey;

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
                .distanceInKilometers(segmentResponse.getDistance())
                .build();
    }

    private URI createUri(Place from, Place to, String profile) {
        try (Formatter formatter = new Formatter(Locale.US)) {
            return URI.create(formatter.format("%s/directions/%s?api_key=%s&start=%f,%f&end=%f,%f",
                    baseUri,
                    profile,
                    apiKey,
                    from.getGeoCoordinate().getLatitude(),
                    from.getGeoCoordinate().getLongitude(),
                    to.getGeoCoordinate().getLatitude(),
                    to.getGeoCoordinate().getLongitude()).toString());
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