package com.tuimm.learningpath.trips;

import com.tuimm.learningpath.exceptions.EntityNotFoundException;
import com.tuimm.learningpath.trips.dal.TripEntity;
import com.tuimm.learningpath.trips.dal.TripsDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Repository
@RequiredArgsConstructor
public class JPATripsRepository implements TripsRepository {
    private final TripsDao dao;
    private final TripEntityMapper mapper;
    @Override
    public Collection<Trip> findAll() {
        return StreamSupport.stream(dao.findAll().spliterator(), false)
                .map(mapper::mapTripEntityToTrip)
                .toList();
    }

    @Override
    public Trip findById(UUID id) {
        return mapper.mapTripEntityToTrip(findTripByIdOrThrowNotFoundException(id));
    }

    private TripEntity findTripByIdOrThrowNotFoundException(UUID id) {
        return dao.findById(id)
                .orElseThrow(() -> {throw new EntityNotFoundException("Trip", id.toString()); });
    }
}
