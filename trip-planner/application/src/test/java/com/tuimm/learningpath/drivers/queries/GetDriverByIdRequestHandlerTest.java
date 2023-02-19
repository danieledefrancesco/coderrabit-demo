package com.tuimm.learningpath.drivers.queries;

import com.tuimm.learningpath.drivers.Driver;
import com.tuimm.learningpath.drivers.DriversRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;

class GetDriverByIdRequestHandlerTest {
    private DriversRepository driversRepository;
    private GetDriverByIdRequestHandler getDriverByIdRequestHandler;

    @BeforeEach
    void setUp() {
        driversRepository = mock(DriversRepository.class);
        getDriverByIdRequestHandler = new GetDriverByIdRequestHandler(driversRepository);
    }

    @Test
    void handle_shouldReturnExpectedResult() {
        UUID driverId = UUID.fromString("00000000-0000-0000-0000-000000000001");
        Driver driver = mock(Driver.class);
        when(driversRepository.findById(driverId)).thenReturn(driver);
        Assertions.assertEquals(driver, getDriverByIdRequestHandler.handle(GetDriverByIdRequest.fromId(driverId)));
        verify(driversRepository).findById(driverId);
    }
}
