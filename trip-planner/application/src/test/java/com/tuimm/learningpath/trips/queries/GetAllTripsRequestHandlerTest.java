package com.tuimm.learningpath.trips.queries;

import com.tuimm.learningpath.trips.Trip;
import com.tuimm.learningpath.trips.TripsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;

import static org.mockito.Mockito.*;

class GetAllTripsRequestHandlerTest {
    private TripsRepository tripsRepository;
    private GetAllTripsRequestHandler getAllTripsRequestHandler;

    @BeforeEach
    void setUp() {
        tripsRepository = mock(TripsRepository.class);
        getAllTripsRequestHandler = new GetAllTripsRequestHandler(tripsRepository);
    }

    @Test
    void handle_shouldReturnExpectedResult() {
        Collection<Trip> allTrips = Collections.emptyList();
        when(tripsRepository.findAll()).thenReturn(allTrips);
        Assertions.assertEquals(allTrips,
                getAllTripsRequestHandler.handle(GetAllTripsRequest.create()).getTrips());
        verify(tripsRepository).findAll();
    }
}
