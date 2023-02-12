package com.tuimm.learningpath.infrastructure.vehicles;

import com.googlecode.concurrenttrees.common.Iterables;
import com.tuimm.learningpath.domain.vehicles.*;
import com.tuimm.learningpath.infrastructure.IntegrationTest;
import com.tuimm.learningpath.infrastructure.vehicles.dal.VehicleDao;
import com.tuimm.learningpath.infrastructure.vehicles.dal.VehicleEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.UUID;

class JPAGarageTest extends IntegrationTest {
    private static final String model = "model";
    private static final double dailyRentPrice = 2;
    private static final double averageSpeed = 3;
    private static final double autonomy = 4;
    private static final int stopTimeInSeconds = 5;
    private static final String plate = "AA000BB";
    private static final String fuelType = "PETROL";
    private static final double emissions = 6;
    private static final double fuelConsumption = 7;
    @Autowired
    private JPAGarage garage;
    @Autowired
    private VehicleDao dao;

    @BeforeEach
    public void cleanUp() {
        dao.deleteAll();
    }

    @Test
    void getAllVehicles_shouldReturnAllTheVehicles() {
        UUID carId = UUID.randomUUID();
        UUID pullmanId = UUID.randomUUID();
        dao.save(createVehicle(carId, VehicleEntity.VehicleType.CAR, 1));
        dao.save(createVehicle(pullmanId, VehicleEntity.VehicleType.PULLMAN, 1));
        Collection<Vehicle> vehicles = garage.getAllVehicles();

        Vehicle carAsVehicle = vehicles.stream()
                .filter(v -> v.getId().equals(carId))
                .findFirst()
                .orElse(null);
        Vehicle pullmanAsVehicle = vehicles.stream()
                .filter(v -> v.getId().equals(pullmanId))
                .findFirst()
                .orElse(null);

        Assertions.assertEquals(2, vehicles.size());
        Assertions.assertNotNull(carAsVehicle);
        Assertions.assertNotNull(pullmanAsVehicle);
        Car car = Assertions.assertInstanceOf(Car.class, carAsVehicle);
        Pullman pullman = Assertions.assertInstanceOf(Pullman.class, pullmanAsVehicle);
        assertVehicleHasExpectedValues(car, carId, 1);
        assertVehicleHasExpectedValues(pullman, pullmanId, 1);
    }

    @Test
    void getSuitableVehicles_shouldReturnOnlyTheVehiclesWithAValueOfMaxPeopleGreaterThanOrEqualToTheProvidedValue() {
        UUID carId = UUID.randomUUID();
        UUID pullmanId = UUID.randomUUID();
        dao.save(createVehicle(carId, VehicleEntity.VehicleType.CAR, 1));
        dao.save(createVehicle(pullmanId, VehicleEntity.VehicleType.PULLMAN, 2));
        Collection<Vehicle> vehicles = garage.getSuitableVehicles(2);

        Vehicle pullmanAsVehicle = vehicles.stream()
                .filter(v -> v.getId().equals(pullmanId))
                .findFirst()
                .orElse(null);

        Assertions.assertEquals(1, vehicles.size());
        Assertions.assertNotNull(pullmanAsVehicle);
        Pullman pullman = Assertions.assertInstanceOf(Pullman.class, pullmanAsVehicle);
        assertVehicleHasExpectedValues(pullman, pullmanId, 2);
    }

    @Test
    void addVehicle_shouldAddVehicle() {
        UUID carId = UUID.randomUUID();
        garage.addVehicle(createCar(carId, 2));

        Assertions.assertEquals(1, Iterables.count(dao.findAll()));
        VehicleEntity vehicleEntity = dao.findById(carId).orElseThrow();

        assertVehicleHasExpectedValues(vehicleEntity, carId, 2, VehicleEntity.VehicleType.CAR);
    }

    @Test
    void delete_shouldDeleteVehicle() {
        UUID id = UUID.randomUUID();
        VehicleEntity entity = createVehicle(id, VehicleEntity.VehicleType.CAR, 3);
        dao.save(entity);
        garage.delete(id);
        Assertions.assertEquals(0, Iterables.count(dao.findAll()));
    }

    private void assertVehicleHasExpectedValues(VehicleEntity vehicle, UUID id, int maxPeople, VehicleEntity.VehicleType type)
    {
        Assertions.assertEquals(id, vehicle.getId());
        Assertions.assertEquals(maxPeople, vehicle.getMaxPeople());
        Assertions.assertEquals(model, vehicle.getModel());
        Assertions.assertEquals(dailyRentPrice, vehicle.getDailyRentPrice(), 0);
        Assertions.assertEquals(averageSpeed, vehicle.getAverageSpeed(), 0);
        Assertions.assertEquals(autonomy, vehicle.getAutonomy(), 0);
        Assertions.assertEquals(stopTimeInSeconds, vehicle.getStopTimeInSeconds());
        Assertions.assertEquals(plate, vehicle.getPlate());
        Assertions.assertEquals(fuelType, vehicle.getFuelType());
        Assertions.assertEquals(emissions, vehicle.getEmissions(), 0);
        Assertions.assertEquals(fuelConsumption, vehicle.getFuelConsumption(), 0);
        Assertions.assertEquals(type, vehicle.getType());
    }

    private void assertVehicleHasExpectedValues(EnginePoweredVehicle vehicle, UUID id, int maxPeople)
    {
        Assertions.assertEquals(id, vehicle.getId());
        Assertions.assertEquals(maxPeople, vehicle.getMaxPeople());
        Assertions.assertEquals(model, vehicle.getModel());
        Assertions.assertEquals(dailyRentPrice, vehicle.getDailyRentPrice(), 0);
        Assertions.assertEquals(averageSpeed, vehicle.getAverageSpeed(), 0);
        Assertions.assertEquals(autonomy, vehicle.getAutonomy(), 0);
        Assertions.assertEquals(stopTimeInSeconds, vehicle.getStopTimeInSeconds());
        Assertions.assertEquals(plate, vehicle.getPlate().getValue());
        Assertions.assertEquals(fuelType, vehicle.getFuelType().name());
        Assertions.assertEquals(emissions, vehicle.getEmissions(), 0);
        Assertions.assertEquals(fuelConsumption, vehicle.getFuelConsumption(), 0);
    }

    private Car createCar(UUID id, int maxPeople) {
        return new Car(id,
                model,
                maxPeople,
                dailyRentPrice,
                averageSpeed,
                autonomy,
                stopTimeInSeconds,
                GenericPlate.from(plate),
                FuelType.valueOf(fuelType),
                emissions,
                fuelConsumption);
    }

    private VehicleEntity createVehicle(UUID id, VehicleEntity.VehicleType type, int maxPeople) {
        VehicleEntity result = new VehicleEntity();
        result.setId(id);
        result.setMaxPeople(maxPeople);
        result.setModel(model);
        result.setDailyRentPrice(dailyRentPrice);
        result.setAverageSpeed(averageSpeed);
        result.setAutonomy(autonomy);
        result.setStopTimeInSeconds(stopTimeInSeconds);
        result.setPlate(plate);
        result.setFuelType(fuelType);
        result.setEmissions(emissions);
        result.setFuelConsumption(fuelConsumption);
        result.setType(type);
        return result;
    }
}
