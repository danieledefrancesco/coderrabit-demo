package com.tuimm.learningpath.trips.commands;

import com.tuimm.learningpath.trips.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class CreateTripCommandTest {
    private TripFactory tripFactory;
    private TripPlanner tripPlanner;
    private TripsRepository tripsRepository;
    private CreateTripCommand createTripCommand;

    @BeforeEach
    void setUp() {
        tripFactory = mock(TripFactory.class);
        tripPlanner = mock(TripPlanner.class);
        tripsRepository = mock(TripsRepository.class);
        createTripCommand = new CreateTripCommand(tripFactory, tripPlanner, tripsRepository);
    }

    @Test
    void handle_shouldReturnExpectedResult() {
        TripPlan plan = mock(TripPlan.class);
        Trip trip = mock(Trip.class);
        CreateTripRequest createTripRequest = mock(CreateTripRequest.class);

        when(tripPlanner.planTrip(createTripRequest)).thenReturn(plan);
        when(tripFactory.create(plan)).thenReturn(trip);

        Assertions.assertEquals(trip, createTripCommand.handle(createTripRequest));

        verify(tripPlanner).planTrip(createTripRequest);
        verify(tripFactory).create(plan);
        verify(tripsRepository).add(trip);
    }
}
