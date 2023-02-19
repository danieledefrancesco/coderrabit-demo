package com.tuimm.learningpath.drivers.queries;

import com.tuimm.learningpath.drivers.Driver;
import com.tuimm.learningpath.drivers.DriversRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;

import static org.mockito.Mockito.*;

class GetAllDriversRequestHandlerTest {
    private DriversRepository driversRepository;
    private GetAllDriversRequestHandler getAllDriversRequestHandler;

    @BeforeEach
    void setUp() {
        driversRepository = mock(DriversRepository.class);
        getAllDriversRequestHandler = new GetAllDriversRequestHandler(driversRepository);
    }

    @Test
    void handle_shouldReturnExpectedResult() {
        Collection<Driver> allDrivers = Collections.emptyList();
        when(driversRepository.findAll()).thenReturn(allDrivers);
        Assertions.assertEquals(allDrivers,
                getAllDriversRequestHandler.handle(GetAllDriversRequest.create()).getDrivers());
        verify(driversRepository).findAll();
    }
}
