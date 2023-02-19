package com.tuimm.learningpath.vehicles.commands;

import com.tuimm.learningpath.vehicles.Garage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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
        deleteVehicleCommand.handle(request);
        verify(garage).delete(id);
    }
}
