package com.tuimm.learningpath.trips.commands;

import com.tuimm.learningpath.trips.Trip;
import com.tuimm.learningpath.trips.TripsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;

class DeleteTripCommandTest {
    private TripsRepository tripsRepository;
    private DeleteTripCommand deleteTripCommand;

    @BeforeEach
    void setUp() {
        tripsRepository = mock(TripsRepository.class);
        deleteTripCommand = new DeleteTripCommand(tripsRepository);
    }

    @Test
    void handle_shouldInvokeRepository() {
        UUID id = UUID.fromString("00000000-0000-0000-0000-000000000001");
        DeleteTripRequest request = DeleteTripRequest.fromId(id);
        Trip trip = mock(Trip.class);
        when(tripsRepository.findById(id)).thenReturn(trip);
        deleteTripCommand.handle(request);
        verify(trip).delete();
        verify(tripsRepository).findById(id);
    }
}
