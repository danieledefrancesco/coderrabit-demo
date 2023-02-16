package com.tuimm.learningpath.trips;

import com.tuimm.learningpath.RandomIdGenerator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TripFactoryImpl implements TripFactory {
    RandomIdGenerator randomIdGenerator;
    @Override
    public Trip create(TripPlan plan) {
        return Trip.builder()
                .id(randomIdGenerator.generateRandomId())
                .plan(plan)
                .build();
    }
}
