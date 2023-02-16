package com.tuimm.learningpath.vehicles.queries;

import com.tuimm.learningpath.mediator.RequestHandler;
import com.tuimm.learningpath.vehicles.Garage;
import com.tuimm.learningpath.vehicles.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class GetVehicleByIdRequestHandler extends RequestHandler<GetVehicleByIdRequest, Vehicle> {
    private final Garage garage;
    public GetVehicleByIdRequestHandler(Garage garage) {
        super(GetVehicleByIdRequest.class);
        this.garage = garage;
    }
    @Override
    public Vehicle handle(GetVehicleByIdRequest request) {
        return garage.findById(request.getId());
    }
}
