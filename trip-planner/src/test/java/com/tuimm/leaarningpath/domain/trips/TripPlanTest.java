package com.tuimm.leaarningpath.domain.trips;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

class TripPlanTest {
    private TripPlan tripPlan;
    List<StagePlan> stagePlans;
    private StagePlan firstStage;
    private StagePlan lastStage;

    @BeforeEach
    void setUp() {
        stagePlans = new LinkedList<>();
        tripPlan = spy(TripPlan.create(stagePlans));
        firstStage = mock(StagePlan.class);
        lastStage = mock(StagePlan.class);
        stagePlans.add(firstStage);
        stagePlans.add(lastStage);
    }

    @Test
    void getTotalEmissions_shouldReturnExpectedResult() {
        double firstStageEmissions = 10;
        double lastStageEmissions = 20;
        double expectedTripEmissions = 30;

        when(firstStage.getEmissions()).thenReturn(firstStageEmissions);
        when(lastStage.getEmissions()).thenReturn(lastStageEmissions);

        Assertions.assertEquals(expectedTripEmissions,
                tripPlan.getTotalEmissions(),
                0);

        verify(firstStage, times(1)).getEmissions();
        verify(lastStage, times(1)).getEmissions();
    }
    
    @Test
    void getTotalPrice_shouldReturnExpectedResult() {
        double firstStagePrice = 10;
        double lastStagePrice = 20;
        double expectedTripPrice = 30;

        when(firstStage.getPrice()).thenReturn(firstStagePrice);
        when(lastStage.getPrice()).thenReturn(lastStagePrice);

        Assertions.assertEquals(expectedTripPrice,
                tripPlan.getTotalPrice(),
                0);

        verify(firstStage, times(1)).getPrice();
        verify(lastStage, times(1)).getPrice();
    }

    @Test
    void getTripDuration_shouldReturnExpectedResult() {
        Duration firstStageDuration = Duration.ofSeconds(10);
        Duration lastStageDuration = Duration.ofSeconds(20);
        Duration expectedTripDuration = Duration.ofSeconds(30);

        when(firstStage.getDuration()).thenReturn(firstStageDuration);
        when(lastStage.getDuration()).thenReturn(lastStageDuration);

        Assertions.assertEquals(expectedTripDuration, tripPlan.getTripDuration());

        verify(firstStage, times(1)).getDuration();
        verify(lastStage, times(1)).getDuration();
    }

    @Test
    void getArrivalTime_shouldReturnExpectedResult() {
        LocalDateTime expectedArrivalDateTime =
                LocalDateTime.of(2023,1,1,9,0,0);

        when(lastStage.getArrivalDateTime()).thenReturn(expectedArrivalDateTime);

        Assertions.assertEquals(expectedArrivalDateTime, tripPlan.getArrivalDateTime());
        verify(lastStage, times(1)).getArrivalDateTime();
    }

    @Test
    void toString_shouldReturnExpectedValue() {
        Duration tripDuration = Duration.ofSeconds(100);
        LocalDateTime arrivalDateTime = LocalDateTime.parse("2023-01-01T09:00:00");
        double totalPrice = 10;
        double emissions = 15;

        when(tripPlan.getTripDuration()).thenReturn(tripDuration);
        when(tripPlan.getArrivalDateTime()).thenReturn(arrivalDateTime);
        when(tripPlan.getTotalPrice()).thenReturn(totalPrice);
        when(tripPlan.getTotalEmissions()).thenReturn(emissions);

        String expected = String.format("TripPlan:%s", System.lineSeparator()) +
                String.format(" duration: 100 s%s", System.lineSeparator()) +
                String.format(" arrivalDateTime: 2023-01-01 09:00:00%s",
                        System.lineSeparator()) +
                String.format(" totalPrice: %f%s", totalPrice, System.lineSeparator()) +
                String.format(" totalEmissions: %f CO2/d%s", emissions, System.lineSeparator()) +
                String.format(" stages: %s%s", stagePlans, System.lineSeparator());

        clearInvocations(tripPlan);

        Assertions.assertEquals(expected, tripPlan.toString());
        verify(tripPlan, times(1)).getTripDuration();
        verify(tripPlan, times(1)).getArrivalDateTime();
        verify(tripPlan, times(1)).getTotalPrice();
        verify(tripPlan, times(1)).getTotalEmissions();
    }
}
