package com.tuimm.learningpath.vehicles.queries;

import com.tuimm.learningpath.mediator.RequestHandler;
import com.tuimm.learningpath.vehicles.Garage;
import org.springframework.stereotype.Component;
@Component
public class GetAllVehiclesRequestHandler extends RequestHandler<GetAllVehiclesRequest, GetVehiclesResponse> {
    private final Garage garage;
    public GetAllVehiclesRequestHandler(Garage garage) {
        super(GetAllVehiclesRequest.class);
        this.garage = garage;
    }

    @Override
    public GetVehiclesResponse handle(GetAllVehiclesRequest request) {
        return GetVehiclesResponse.from(garage.getAllVehicles());
    }
}
