package com.tuimm.learningpath.places;

import com.tuimm.learningpath.IntegrationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class PlacesServiceImplTest extends IntegrationTest {
    @Autowired
    private PlacesServiceImpl placesService;
    @Test
    void fromName_shouldReturnExpectedPlace() {
        Place expectedPlace = Place.create("Rome", GeoCoordinate.of(41.878243, 12.52809));
        Place actualPlace = placesService.fromName("Rome");
        Assertions.assertEquals(expectedPlace, actualPlace);
    }
}
