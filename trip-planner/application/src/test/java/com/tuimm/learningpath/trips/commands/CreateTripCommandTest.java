package com.tuimm.learningpath.trips.commands;

import com.tuimm.learningpath.drivers.Driver;
import com.tuimm.learningpath.mediator.Mediator;
import com.tuimm.learningpath.trips.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

import static org.mockito.Mockito.*;

class CreateTripCommandTest {
    private TripFactory tripFactory;
    private TripPlanner tripPlanner;
    private CreateTripCommand createTripCommand;
    private Mediator mediator;

    @BeforeEach
    void setUp() {
        tripFactory = mock(TripFactory.class);
        tripPlanner = mock(TripPlanner.class);
        mediator = mock(Mediator.class);
        createTripCommand = new CreateTripCommand(tripFactory, tripPlanner, mediator);
    }

    @Test
    void handle_shouldReturnExpectedResult() {
        TripPlan plan = mock(TripPlan.class);
        Trip trip = mock(Trip.class);
        UUID driverId = UUID.randomUUID();
        CreateTripRequest createTripRequest = CreateTripRequest.builder()
                .start(LocalDateTime.now())
                .numberOfPeople(1)
                .stages(Collections.singletonList(CreateStageRequest.builder()
                        .from("Rome")
                        .to("Milan")
                        .driverId(driverId)
                        .preferredPlanPolicy(PlanByLessPolluting.getInstance())
                        .build()))
                .build();
        Driver driver = mock(Driver.class);
        when(driver.getId()).thenReturn(driverId);

        when(tripPlanner.planTrip(any())).thenReturn(plan);
        when(tripFactory.create(plan)).thenReturn(trip);
        when(mediator.send(any())).thenReturn(driver);

        Assertions.assertEquals(trip, createTripCommand.handle(createTripRequest));

        verify(tripPlanner).planTrip(any());
        verify(tripFactory).create(plan);
        verify(trip).save();
    }
}
