package com.tuimm.learningpath.vehicles.queries;

import com.tuimm.learningpath.vehicles.FuelType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;

class GetAllFuelTypesRequestHandlerTest {
    private GetAllFuelTypesRequestHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GetAllFuelTypesRequestHandler();
    }

    @Test
    void handle_shouldReturnExpectedResult() {
        Collection<FuelType> allFuelTypes = handler.handle(GetAllFuelTypesRequest.create()).getFuelTypes();
        Arrays.stream(FuelType.values()).forEach(fuelType -> Assertions.assertTrue(allFuelTypes.contains(fuelType)));
    }
}
