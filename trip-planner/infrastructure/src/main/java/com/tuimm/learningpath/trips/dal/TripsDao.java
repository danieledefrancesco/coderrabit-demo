package com.tuimm.learningpath.trips.dal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TripsDao extends CrudRepository<TripEntity, UUID> {
}
