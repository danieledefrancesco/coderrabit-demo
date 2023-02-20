package com.tuimm.learningpath.vehicles.commands;

import com.tuimm.learningpath.exceptions.EntityNotFoundException;
import com.tuimm.learningpath.mediator.RequestHandler;
import com.tuimm.learningpath.vehicles.FuelType;
import org.springframework.stereotype.Component;

@Component
public class UpdateFuelTypeCostCommand extends RequestHandler<UpdateFuelTypeCostRequest, Void> {
    public UpdateFuelTypeCostCommand() {
        super(UpdateFuelTypeCostRequest.class);
    }

    @Override
    public Void handle(UpdateFuelTypeCostRequest request) {
        try {
            FuelType fuelType = FuelType.valueOf(request.getFuelType());
            fuelType.setCost(request.getCost());
        }
        catch (IllegalArgumentException e) {
            throw new EntityNotFoundException("Fuel type", request.getFuelType());
        }
        return null;
    }
}
