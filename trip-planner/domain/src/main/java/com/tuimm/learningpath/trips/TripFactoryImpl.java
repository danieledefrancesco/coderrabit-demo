package com.tuimm.learningpath.trips;

import com.tuimm.learningpath.RandomIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TripFactoryImpl implements TripFactory {
    private final RandomIdGenerator randomIdGenerator;
    private final TripAggregateManager aggregateManager;
    @Override
    public Trip create(TripPlan plan) {
        Trip trip = Trip.builder()
                .id(randomIdGenerator.generateRandomId())
                .plan(plan)
                .aggregateManager(aggregateManager)
                .build();
        trip.addEvent(OnTripCreatedEvent.of(trip));
        return trip;
    }
}
