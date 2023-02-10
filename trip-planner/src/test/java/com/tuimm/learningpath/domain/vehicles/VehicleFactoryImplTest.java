package com.tuimm.learningpath.domain.vehicles;

import com.tuimm.learningpath.common.RandomIdGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

class VehicleFactoryImplTest {
    private VehicleFactoryImpl vehicleFactory;
    private RandomIdGenerator randomIdGenerator;
    private final UUID randomId = UUID.randomUUID();

    @BeforeEach
    public void setUp() {
        randomIdGenerator = Mockito.mock(RandomIdGenerator.class);
        Mockito.when(randomIdGenerator.generateRandomId()).thenReturn(randomId);
        vehicleFactory = new VehicleFactoryImpl(randomIdGenerator);
    }

    @Test
    void createBike_shouldCreateANewBikeHavingTheIdProvidedByTheRandomIdGenerator() {
        Bike bike = vehicleFactory.createBike("model", 1, 2, 3, 4);
        Assertions.assertEquals(randomId, bike.getId());
        Mockito.verify(randomIdGenerator, Mockito.times(1)).generateRandomId();
    }

    @Test
    void createCar_shouldCreateANewCarHavingTheIdProvidedByTheRandomIdGenerator() {
        Car car = vehicleFactory.createCar("model",
                1,
                2,
                3,
                4,
                5,
                "AA000BB",
                FuelType.PETROL,
                6,
                7);
        Assertions.assertEquals(randomId, car.getId());
        Mockito.verify(randomIdGenerator, Mockito.times(1)).generateRandomId();
    }

    @Test
    void createPullman_shouldCreateANewPullmanHavingTheIdProvidedByTheRandomIdGenerator() {
        Pullman pullman = vehicleFactory.createPullman("model",
                1,
                2,
                3,
                4,
                5,
                "AA000BB",
                FuelType.PETROL,
                6,
                7);
        Assertions.assertEquals(randomId, pullman.getId());
        Mockito.verify(randomIdGenerator, Mockito.times(1)).generateRandomId();

    }

    @Test
    void createScooter_shouldCreateANewScooterHavingTheIdProvidedByTheRandomIdGenerator() {
        Scooter scooter = vehicleFactory.createScooter("model",
                1,
                2,
                3,
                4,
                5,
                "AA0000",
                FuelType.PETROL,
                6,
                7);
        Assertions.assertEquals(randomId, scooter.getId());
        Mockito.verify(randomIdGenerator, Mockito.times(1)).generateRandomId();
    }
}
