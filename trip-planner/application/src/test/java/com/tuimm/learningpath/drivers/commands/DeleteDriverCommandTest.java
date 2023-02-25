package com.tuimm.learningpath.drivers.commands;

import com.tuimm.learningpath.drivers.Driver;
import com.tuimm.learningpath.drivers.DriversRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;

class DeleteDriverCommandTest {
    private DriversRepository driversRepository;
    private DeleteDriverCommand deleteDriverCommand;

    @BeforeEach
    void setUp() {
        driversRepository = mock(DriversRepository.class);
        deleteDriverCommand = new DeleteDriverCommand(driversRepository);
    }

    @Test
    void handle_shouldInvokeRepository() {
        UUID id = UUID.fromString("00000000-0000-0000-0000-000000000001");
        DeleteDriverRequest request = DeleteDriverRequest.fromId(id);
        Driver driver = mock(Driver.class);
        when(driversRepository.findById(id)).thenReturn(driver);
        deleteDriverCommand.handle(request);
        verify(driver).delete();
        verify(driversRepository).findById(id);
    }
}
