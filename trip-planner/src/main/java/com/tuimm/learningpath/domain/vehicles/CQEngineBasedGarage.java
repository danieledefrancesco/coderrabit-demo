package com.tuimm.learningpath.domain.vehicles;

import com.googlecode.cqengine.IndexedCollection;
import com.googlecode.cqengine.attribute.Attribute;
import com.googlecode.cqengine.attribute.SimpleAttribute;
import com.googlecode.cqengine.index.hash.HashIndex;
import com.googlecode.cqengine.index.navigable.NavigableIndex;
import com.googlecode.cqengine.query.Query;
import com.googlecode.cqengine.query.option.QueryOptions;
import com.googlecode.cqengine.resultset.ResultSet;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.UUID;

import static com.googlecode.cqengine.query.QueryFactory.*;
@Component
public class CQEngineBasedGarage implements Garage {
    private static final Attribute<Vehicle, UUID> ID_ATTRIBUTE = new SimpleAttribute<>() {
        @Override
        public UUID getValue(Vehicle o, QueryOptions queryOptions) {
            return o.getId();
        }
    };

    private static final Attribute<Vehicle, Integer> MAX_PEOPLE_ATTRIBUTE = new SimpleAttribute<>() {
        @Override
        public Integer getValue(Vehicle o, QueryOptions queryOptions) {
            return o.getMaxPeople();
        }
    };

    private final IndexedCollection<Vehicle> indexedCollection;

    public CQEngineBasedGarage(IndexedCollection<Vehicle> indexedCollection) {
        this.indexedCollection = indexedCollection;
        indexedCollection.addIndex(HashIndex.onAttribute(ID_ATTRIBUTE));
        indexedCollection.addIndex(NavigableIndex.onAttribute(MAX_PEOPLE_ATTRIBUTE));
    }

    @Override
    public Collection<Vehicle> getAllVehicles() {
        return indexedCollection.stream().toList();
    }

    @Override
    public Collection<Vehicle> getSuitableVehicles(int numberOfPeople) {
        Query<Vehicle> query = greaterThanOrEqualTo(MAX_PEOPLE_ATTRIBUTE, numberOfPeople);
        try (ResultSet<Vehicle> result = indexedCollection.retrieve(query)) {
            return result.stream().toList();
        }
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        Query<Vehicle> query = equal(ID_ATTRIBUTE, vehicle.getId());
        try (ResultSet<Vehicle> result = indexedCollection.retrieve(query)) {
            if (result.size() > 0) {
                throw new IllegalArgumentException(String.format("vehicle with id %s il already present", vehicle.getId()));
            }
        }
        indexedCollection.add(vehicle);
    }

    @Override
    public void delete(UUID id) {
        Query<Vehicle> query = equal(ID_ATTRIBUTE, id);
        try (ResultSet<Vehicle> result = indexedCollection.retrieve(query)) {
            result.stream().findFirst().ifPresent(indexedCollection::remove);
        }
    }
}
