package com.tuimm.learningpath.vehicles.queries;

import com.tuimm.learningpath.vehicles.Garage;
import com.tuimm.learningpath.vehicles.Vehicle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;

import static org.mockito.Mockito.*;

class GetAllVehiclesRequestHandlerTest {
    private Garage garage;
    private GetAllVehiclesRequestHandler getAllVehiclesRequestHandler;

    @BeforeEach
    void setUp() {
        garage = mock(Garage.class);
        getAllVehiclesRequestHandler = new GetAllVehiclesRequestHandler(garage);
    }

    @Test
    void handle_shouldReturnExpectedResult() {
        Collection<Vehicle> allVehicles = Collections.emptyList();
        when(garage.getAllVehicles()).thenReturn(allVehicles);
        Assertions.assertEquals(allVehicles,
                getAllVehiclesRequestHandler.handle(GetAllVehiclesRequest.create()).getVehicles());
        verify(garage).getAllVehicles();
    }
}
