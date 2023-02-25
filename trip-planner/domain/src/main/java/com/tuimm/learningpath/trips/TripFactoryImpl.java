package com.tuimm.learningpath.trips;

import com.tuimm.learningpath.RandomIdGenerator;
import com.tuimm.learningpath.TodayDateProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TripFactoryImpl implements TripFactory {
    private final RandomIdGenerator randomIdGenerator;
    private final TripAggregateManager aggregateManager;
    private final TodayDateProvider todayDateProvider;
    @Override
    public Trip create(TripPlan plan) {
        Trip trip = Trip.builder()
                .id(randomIdGenerator.generateRandomId())
                .plan(plan)
                .aggregateManager(aggregateManager)
                .todayDateProvider(todayDateProvider)
                .build();
        trip.addEvent(OnTripCreatedEvent.of(trip));
        return trip;
    }
}
