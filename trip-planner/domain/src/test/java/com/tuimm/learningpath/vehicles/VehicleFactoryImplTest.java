package com.tuimm.learningpath.vehicles;

import com.tuimm.learningpath.RandomIdGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

class VehicleFactoryImplTest {
    private VehicleFactoryImpl vehicleFactory;
    private RandomIdGenerator randomIdGenerator;
    private VehicleAggregateManager aggregateManager;
    private final UUID randomId = UUID.randomUUID();

    @BeforeEach
    public void setUp() {
        randomIdGenerator = Mockito.mock(RandomIdGenerator.class);
        Mockito.when(randomIdGenerator.generateRandomId()).thenReturn(randomId);
        aggregateManager = Mockito.mock(VehicleAggregateManager.class);
        vehicleFactory = new VehicleFactoryImpl(randomIdGenerator, aggregateManager);
    }

    @Test
    void createBike_shouldCreateANewBikeHavingTheIdProvidedByTheRandomIdGenerator() {
        Bike bike = vehicleFactory.createBike(builder ->
                builder.model("model")
                        .maxPeople(1)
                        .dailyRentPrice(2)
                        .averageSpeed(3)
                        .autonomy(4));
        Assertions.assertEquals(randomId, bike.getId());
        Mockito.verify(randomIdGenerator, Mockito.times(1)).generateRandomId();
    }

    @Test
    void createCar_shouldCreateANewCarHavingTheIdProvidedByTheRandomIdGenerator() {
        Car car = vehicleFactory.createCar(builder ->
                builder.plate(GenericPlate.from("AA000BB"))
                        .fuelType(FuelType.PETROL)
                        .stopTimeInSeconds(5)
                        .emissions(6)
                        .fuelConsumption(7)
                        .model("model")
                        .maxPeople(1)
                        .dailyRentPrice(2)
                        .averageSpeed(3)
                        .autonomy(4));
        Assertions.assertEquals(randomId, car.getId());
        Mockito.verify(randomIdGenerator, Mockito.times(1)).generateRandomId();
    }

    @Test
    void createPullman_shouldCreateANewPullmanHavingTheIdProvidedByTheRandomIdGenerator() {
        Pullman pullman = vehicleFactory.createPullman(builder ->
                builder.plate(GenericPlate.from("AA000BB"))
                        .fuelType(FuelType.PETROL)
                        .stopTimeInSeconds(5)
                        .emissions(6)
                        .fuelConsumption(7)
                        .model("model")
                        .maxPeople(1)
                        .dailyRentPrice(2)
                        .averageSpeed(3)
                        .autonomy(4));
        Assertions.assertEquals(randomId, pullman.getId());
        Mockito.verify(randomIdGenerator, Mockito.times(1)).generateRandomId();

    }

    @Test
    void createScooter_shouldCreateANewScooterHavingTheIdProvidedByTheRandomIdGenerator() {
        Scooter scooter = vehicleFactory.createScooter(builder ->
                builder.plate(ScooterPlate.from("AA0000"))
                        .fuelType(FuelType.PETROL)
                        .stopTimeInSeconds(5)
                        .emissions(6)
                        .fuelConsumption(7)
                        .model("model")
                        .maxPeople(1)
                        .dailyRentPrice(2)
                        .averageSpeed(3)
                        .autonomy(4));
        Assertions.assertEquals(randomId, scooter.getId());
        Mockito.verify(randomIdGenerator, Mockito.times(1)).generateRandomId();
    }
}
