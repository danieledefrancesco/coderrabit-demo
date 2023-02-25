package com.tuimm.learningpath.vehicles;

import com.tuimm.learningpath.IntegrationTest;
import com.tuimm.learningpath.exceptions.EntityNotFoundException;
import com.tuimm.learningpath.vehicles.dal.VehiclesDao;
import com.tuimm.learningpath.vehicles.dal.VehicleEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.StreamSupport;

@IntegrationTest
class JPAGarageTest {
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
    private VehiclesDao dao;

    @BeforeEach
    public void cleanUp() {
        dao.deleteAll();
    }

    @Test
    void findById_shouldReturnExpectedVehicle_ifVehicleExists() {
        UUID vehicleId = UUID.fromString("00000000-0000-0000-0000-000000000001");
        int maxPeople = 2;
        VehicleEntity vehicleEntity = createVehicle(vehicleId, VehicleEntity.VehicleType.CAR, maxPeople);
        dao.save(vehicleEntity);
        Vehicle vehicle = garage.findById(vehicleId);
        assertVehicleHasExpectedValues((EnginePoweredVehicle) vehicle, vehicleId, maxPeople);
    }

    @Test
    void findById_shouldThrowEntityNotFoundException_ifVehicleDoesNotExist() {
        UUID vehicleId = UUID.fromString("00000000-0000-0000-0000-000000000001");
        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class,
                () -> garage.findById(vehicleId));
        Assertions.assertEquals("Vehicle with id 00000000-0000-0000-0000-000000000001 does not exist.", exception.getMessage());
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
        return Car.builder()
                .plate(GenericPlate.from(plate))
                .fuelType(FuelType.valueOf(fuelType))
                .fuelConsumption(fuelConsumption)
                .emissions(emissions)
                .stopTimeInSeconds(stopTimeInSeconds)
                .id(id)
                .model(model)
                .maxPeople(maxPeople)
                .dailyRentPrice(dailyRentPrice)
                .averageSpeed(averageSpeed)
                .autonomy(autonomy)
                .build();
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
