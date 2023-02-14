package com.tuimm.learningpath.application.vehicles.queries;

import com.tuimm.learningpath.common.mediator.RequestHandler;
import com.tuimm.learningpath.domain.vehicles.Garage;
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
