package com.tuimm.learningpath.vehicles;

import com.tuimm.learningpath.vehicles.dal.VehicleEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static org.mockito.Mockito.*;

class VehicleEntityMapperTest {
    private VehicleEntitiesMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = spy(Mappers.getMapper(VehicleEntitiesMapper.class));
    }

    @Test
    void mapToEntity_shouldInvokeMapToEntityBike_whenVehicleIsBike() {
        VehicleEntity entity = new VehicleEntity();
        Vehicle vehicle = mock(Bike.class);
        when(mapper.mapToEntity((Bike) vehicle)).thenReturn(entity);

        clearInvocations(mapper);
        Assertions.assertEquals(entity, mapper.mapToEntity(vehicle));
        verify(mapper, times(1)).mapToEntity((Bike)vehicle);
    }

    @Test
    void mapToEntity_shouldInvokeMapToEntityCar_whenVehicleIsCar() {
        VehicleEntity entity = new VehicleEntity();
        Vehicle vehicle = mock(Car.class);
        when(((Car)vehicle).getPlate()).thenReturn(mock(Plate.class));
        when(mapper.mapToEntity((Car) vehicle)).thenReturn(entity);

        clearInvocations(mapper);
        Assertions.assertEquals(entity, mapper.mapToEntity(vehicle));
        verify(mapper, times(1)).mapToEntity((Car)vehicle);
    }

    @Test
    void mapToEntity_shouldInvokeMapToEntityPullman_whenVehicleIsPullman() {
        VehicleEntity entity = new VehicleEntity();
        Vehicle vehicle = mock(Pullman.class);
        when(((Pullman)vehicle).getPlate()).thenReturn(mock(Plate.class));
        when(mapper.mapToEntity((Pullman) vehicle)).thenReturn(entity);

        clearInvocations(mapper);
        Assertions.assertEquals(entity, mapper.mapToEntity(vehicle));
        verify(mapper, times(1)).mapToEntity((Pullman)vehicle);
    }

    @Test
    void mapToEntity_shouldInvokeMapToEntityScooter_whenVehicleIsScooter() {
        VehicleEntity entity = new VehicleEntity();
        Vehicle vehicle = mock(Scooter.class);
        when(((Scooter)vehicle).getPlate()).thenReturn(mock(Plate.class));
        when(mapper.mapToEntity((Scooter) vehicle)).thenReturn(entity);

        clearInvocations(mapper);
        Assertions.assertEquals(entity, mapper.mapToEntity(vehicle));
        verify(mapper, times(1)).mapToEntity((Scooter)vehicle);
    }

    @Test
    void mapToEntity_shouldThrowUnsupportedOperationException_whenVehicleTypeIsNotSupported() {
        Assertions.assertThrows(UnsupportedOperationException.class,
                () -> mapper.mapToEntity(mock(Vehicle.class)));
    }

    @Test
    void mapToVehicle_shouldInvokeMapToBike_whenVehicleTypeIsBike() {
        Bike bike = mock(Bike.class);
        VehicleEntity entity = createFromTypeAndPlate(VehicleEntity.VehicleType.BIKE, null);
        when(mapper.mapToBike(entity)).thenReturn(bike);

        clearInvocations(mapper);
        Assertions.assertEquals(bike, mapper.mapToVehicle(entity));
        verify(mapper, times(1)).mapToBike(entity);
    }

    @Test
    void mapToVehicle_shouldInvokeMapToCar_whenVehicleTypeIsCar() {
        Car car = mock(Car.class);
        VehicleEntity entity = createFromTypeAndPlate(VehicleEntity.VehicleType.CAR, "AA000BB");
        entity.setFuelType("PETROL");
        when(mapper.mapToCar(entity)).thenReturn(car);

        clearInvocations(mapper);
        Assertions.assertEquals(car, mapper.mapToVehicle(entity));
        verify(mapper, times(1)).mapToCar(entity);
    }

    @Test
    void mapToVehicle_shouldInvokeMapToPullman_whenVehicleTypeIsPullman() {
        Pullman pullman = mock(Pullman.class);
        VehicleEntity entity = createFromTypeAndPlate(VehicleEntity.VehicleType.PULLMAN, "AA000BB");
        entity.setFuelType("PETROL");
        when(mapper.mapToPullman(entity)).thenReturn(pullman);

        clearInvocations(mapper);
        Assertions.assertEquals(pullman, mapper.mapToVehicle(entity));
        verify(mapper, times(1)).mapToPullman(entity);
    }

    @Test
    void mapToScooter_shouldInvokeMapToScooter_whenVehicleTypeIsScooter() {
        Scooter scooter = mock(Scooter.class);
        VehicleEntity entity = createFromTypeAndPlate(VehicleEntity.VehicleType.SCOOTER, "AA0000");
        entity.setFuelType("PETROL");
        when(mapper.mapToScooter(entity)).thenReturn(scooter);

        clearInvocations(mapper);
        Assertions.assertEquals(scooter, mapper.mapToVehicle(entity));
        verify(mapper, times(1)).mapToScooter(entity);
    }

    private static VehicleEntity createFromTypeAndPlate(VehicleEntity.VehicleType type, String plate) {
        VehicleEntity entity = new VehicleEntity();
        entity.setId(UUID.randomUUID());
        entity.setModel("model");
        entity.setType(type);
        entity.setPlate(plate);
        entity.setMaxPeople(1);
        entity.setAverageSpeed(10);
        entity.setAutonomy(20);
        return entity;
    }
}
