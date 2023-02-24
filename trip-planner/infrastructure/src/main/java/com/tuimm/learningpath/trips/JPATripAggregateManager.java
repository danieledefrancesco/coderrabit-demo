package com.tuimm.learningpath.trips;

import com.tuimm.learningpath.common.DomainEventsProcessor;
import com.tuimm.learningpath.trips.dal.TripsDao;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JPATripAggregateManager implements TripAggregateManager {
    private final TripsDao dao;
    private final TripEntityMapper mapper;
    @Getter
    private final DomainEventsProcessor domainEventsProcessor;

    @Override
    public void save(Trip trip) {
        dao.save(mapper.mapToTripEntity(trip));
    }

    @Override
    public void delete(Trip trip) {
        dao.deleteById(trip.getId());
    }
}
