package com.tuimm.learningpath.vehicles.commands;

import com.tuimm.learningpath.vehicles.Garage;
import com.tuimm.learningpath.vehicles.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;

class DeleteVehicleCommandTest {
    private Garage garage;
    private DeleteVehicleCommand deleteVehicleCommand;

    @BeforeEach
    void setUp() {
        garage = mock(Garage.class);
        deleteVehicleCommand = new DeleteVehicleCommand(garage);
    }

    @Test
    void handle_shouldInvokeRepository() {
        UUID id = UUID.fromString("00000000-0000-0000-0000-000000000001");
        DeleteVehicleRequest request = DeleteVehicleRequest.fromId(id);
        Vehicle vehicle = mock(Vehicle.class);
        when(garage.findById(id)).thenReturn(vehicle);
        deleteVehicleCommand.handle(request);
        verify(garage).findById(id);
        verify(vehicle).delete();
    }
}
