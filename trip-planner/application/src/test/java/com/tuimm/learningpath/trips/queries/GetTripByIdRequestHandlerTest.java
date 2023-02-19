package com.tuimm.learningpath.trips.queries;

import com.tuimm.learningpath.trips.Trip;
import com.tuimm.learningpath.trips.TripsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;

class GetTripByIdRequestHandlerTest {
    private TripsRepository driversRepository;
    private GetTripByIdRequestHandler getTripByIdRequestHandler;

    @BeforeEach
    void setUp() {
        driversRepository = mock(TripsRepository.class);
        getTripByIdRequestHandler = new GetTripByIdRequestHandler(driversRepository);
    }

    @Test
    void handle_shouldReturnExpectedResult() {
        UUID tripId = UUID.fromString("00000000-0000-0000-0000-000000000001");
        Trip trip = mock(Trip.class);
        when(driversRepository.findById(tripId)).thenReturn(trip);
        Assertions.assertEquals(trip, getTripByIdRequestHandler.handle(GetTripByIdRequest.fromId(tripId)));
        verify(driversRepository).findById(tripId);
    }
}
