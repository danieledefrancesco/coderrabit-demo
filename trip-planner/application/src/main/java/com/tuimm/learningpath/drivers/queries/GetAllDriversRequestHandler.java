package com.tuimm.learningpath.drivers.queries;

import com.tuimm.learningpath.drivers.DriversRepository;
import com.tuimm.learningpath.mediator.RequestHandler;
import org.springframework.stereotype.Component;

@Component
public class GetAllDriversRequestHandler extends RequestHandler<GetAllDriversRequest, GetAllDriversResponse> {
    private final DriversRepository driversRepository;

    public GetAllDriversRequestHandler(DriversRepository driversRepository) {
        super(GetAllDriversRequest.class);
        this.driversRepository = driversRepository;
    }

    @Override
    public GetAllDriversResponse handle(GetAllDriversRequest request) {
        GetAllDriversResponse response = new GetAllDriversResponse();
        response.setDrivers(driversRepository.findAll());
        return response;
    }
}
