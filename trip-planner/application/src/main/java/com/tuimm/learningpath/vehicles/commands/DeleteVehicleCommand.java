package com.tuimm.learningpath.vehicles.commands;

import com.tuimm.learningpath.mediator.RequestHandler;
import com.tuimm.learningpath.vehicles.Garage;
import org.springframework.stereotype.Service;

@Service
public class DeleteVehicleCommand extends RequestHandler<DeleteVehicleRequest, Void> {
    private final Garage garage;
    public DeleteVehicleCommand(Garage garage) {
        super(DeleteVehicleRequest.class);
        this.garage = garage;
    }

    @Override
    public Void handle(DeleteVehicleRequest request) {
        garage.delete(request.getDriverId());
        return null;
    }
}
