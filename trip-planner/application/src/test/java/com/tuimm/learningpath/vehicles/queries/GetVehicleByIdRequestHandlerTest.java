package com.tuimm.learningpath.vehicles.queries;

import com.tuimm.learningpath.vehicles.Garage;
import com.tuimm.learningpath.vehicles.Vehicle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;

class GetVehicleByIdRequestHandlerTest {
    private Garage garage;
    private GetVehicleByIdRequestHandler getVehicleByIdRequestHandler;

    @BeforeEach
    void setUp() {
        garage = mock(Garage.class);
        getVehicleByIdRequestHandler = new GetVehicleByIdRequestHandler(garage);
    }

    @Test
    void handle_shouldReturnExpectedResult() {
        UUID vehicleId = UUID.fromString("00000000-0000-0000-0000-000000000001");
        Vehicle vehicle = mock(Vehicle.class);
        when(garage.findById(vehicleId)).thenReturn(vehicle);
        Assertions.assertEquals(vehicle, getVehicleByIdRequestHandler.handle(GetVehicleByIdRequest.fromId(vehicleId)));
        verify(garage).findById(vehicleId);
    }
}
