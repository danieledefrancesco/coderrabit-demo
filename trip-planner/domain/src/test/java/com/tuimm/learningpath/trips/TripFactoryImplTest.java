package com.tuimm.learningpath.trips;

import com.tuimm.learningpath.RandomIdGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TripFactoryImplTest {
    private RandomIdGenerator randomIdGenerator;
    private TripFactoryImpl tripFactory;

    @BeforeEach
    void setUp() {
        randomIdGenerator = mock(RandomIdGenerator.class);
        tripFactory = new TripFactoryImpl(randomIdGenerator);
    }

    @Test
    void create_shouldReturnExpectedTrip() {
        UUID tripId = UUID.fromString("00000000-0000-0000-0000-000000000001");
        TripPlan tripPlan = mock(TripPlan.class);
        when(randomIdGenerator.generateRandomId()).thenReturn(tripId);
        Trip trip = tripFactory.create(tripPlan);
        Assertions.assertEquals(tripId, trip.getId());
        Assertions.assertEquals(tripPlan, trip.getPlan());
    }
}
