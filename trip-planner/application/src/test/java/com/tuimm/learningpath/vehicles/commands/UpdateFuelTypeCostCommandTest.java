package com.tuimm.learningpath.vehicles.commands;

import com.tuimm.learningpath.exceptions.EntityNotFoundException;
import com.tuimm.learningpath.vehicles.FuelType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UpdateFuelTypeCostCommandTest {
    private UpdateFuelTypeCostCommand updateFuelTypeCostCommand;

    @BeforeEach
    void setUp() {
        updateFuelTypeCostCommand = new UpdateFuelTypeCostCommand();
    }

    @Test
    void handle_shouldUpdateFuelTypeCost_whenProvidedFuelTypeExists() {
        double cost = 1.234;
        UpdateFuelTypeCostRequest request = UpdateFuelTypeCostRequest.create("PETROL", cost);
        updateFuelTypeCostCommand.handle(request);
        Assertions.assertEquals(cost, FuelType.PETROL.getCost(), 0);
    }

    @Test
    void handle_shouldThrowEntityNotFoundException_whenProvidedFuelTypeDoesNotExist() {
        UpdateFuelTypeCostRequest request = UpdateFuelTypeCostRequest.create("HYDROGEN", 2);
        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class,
                () -> updateFuelTypeCostCommand.handle(request));
        Assertions.assertEquals("Fuel type with id HYDROGEN does not exist.", exception.getMessage());
    }
}
