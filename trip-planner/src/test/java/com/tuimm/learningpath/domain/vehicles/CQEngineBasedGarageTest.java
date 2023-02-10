package com.tuimm.learningpath.domain.vehicles;

import com.googlecode.cqengine.ConcurrentIndexedCollection;
import com.googlecode.cqengine.IndexedCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CQEngineBasedGarageTest {
    private CQEngineBasedGarage garage;
    private IndexedCollection<Vehicle> vehiclesIndexedCollection;

    @BeforeEach
    public void setUp() {
        vehiclesIndexedCollection = new ConcurrentIndexedCollection<>();
        garage = new CQEngineBasedGarage(vehiclesIndexedCollection);
    }

    @Test
    void getAllVehicles_returnsAllVehicles() {
        List<Vehicle> expectedVehicles = Arrays.stream(new Vehicle[]
                {
                        createVehicleMock(UUID.randomUUID(), 1),
                        createVehicleMock(UUID.randomUUID(), 2),
                }).toList();
        vehiclesIndexedCollection.addAll(expectedVehicles);

        Collection<Vehicle> actualVehicles = garage.getAllVehicles();

        assertVehiclesAreTheSame(expectedVehicles, actualVehicles);
    }

    @Test
    void getVehiclesBySpecification_shouldReturnOnlyVehiclesMatchingSpecification() {
        Vehicle v1 = createVehicleMock(UUID.randomUUID(), 1);
        Vehicle v2 = createVehicleMock(UUID.randomUUID(),2);
        Vehicle v3 = createVehicleMock(UUID.randomUUID(), 3);
        vehiclesIndexedCollection.add(v1);
        vehiclesIndexedCollection.add(v2);
        vehiclesIndexedCollection.add(v3);
        Collection<Vehicle> expectedVehicles = Arrays.stream(new Vehicle[]{v2, v3}).toList();

        Collection<Vehicle> actualVehicles = garage.getSuitableVehicles(2);

        assertVehiclesAreTheSame(expectedVehicles, actualVehicles);
    }

    @Test
    void addVehicle_shouldAddVehicle_whenVehicleWithSameIdIsNotPresent() {
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getId()).thenReturn(UUID.randomUUID());
        Collection<Vehicle> expectedVehicles = Arrays.stream(new Vehicle[]{vehicle}).toList();

        garage.addVehicle(vehicle);

        assertVehiclesAreTheSame(expectedVehicles, vehiclesIndexedCollection);
    }

    @Test
    void addVehicle_shouldThrowIllegalArgumentException_whenVehicleWithSameIdIsPresent() {
        UUID id = UUID.randomUUID();
        Vehicle vehicle = createVehicleMock(id, 1);
        vehiclesIndexedCollection.add(vehicle);
        Vehicle vehicle1 = createVehicleMock(id, 2);

        Exception actualException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> garage.addVehicle(vehicle1));
        Assertions.assertEquals(
                String.format("vehicle with id %s il already present", id),
                actualException.getMessage());
    }

    @Test
    void delete_shouldDeleteVehicle_whenAVehicleWithSameIdIsPresent() {
        UUID id = UUID.randomUUID();
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getId()).thenReturn(id);
        vehiclesIndexedCollection.add(vehicle);

        garage.delete(id);

        Assertions.assertTrue(vehiclesIndexedCollection.isEmpty());
    }

    @Test
    void constructor_shouldThrowNullPointerException_whenANullIndexedCollectionIsProvided() {
        Assertions.assertThrows(NullPointerException.class,
                () -> new CQEngineBasedGarage(null));
    }

    private void assertVehiclesAreTheSame(Collection<Vehicle> expectedVehicles, Collection<Vehicle> actualVehicles) {
        Assertions.assertEquals(expectedVehicles.size(), actualVehicles.size());
        Assertions.assertTrue(expectedVehicles.containsAll(actualVehicles));
        Assertions.assertTrue(actualVehicles.containsAll(expectedVehicles));
    }

    Vehicle createVehicleMock(UUID id, int maxPeople) {
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getId()).thenReturn(id);
        when(vehicle.getMaxPeople()).thenReturn(maxPeople);
        return vehicle;
    }

}
