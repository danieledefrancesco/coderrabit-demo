package com.tuimm.learningpath.drivers.queries;

import com.tuimm.learningpath.drivers.DriversRepository;
import com.tuimm.learningpath.mediator.RequestHandler;
import org.springframework.stereotype.Service;

@Service
public class GetAvailableDriversRequestHandler extends RequestHandler<GetAvailableDriversRequest, GetAvailableDriversResponse> {
    private final DriversRepository driversRepository;
    public GetAvailableDriversRequestHandler(DriversRepository driversRepository) {
        super(GetAvailableDriversRequest.class);
        this.driversRepository = driversRepository;
    }

    @Override
    public GetAvailableDriversResponse handle(GetAvailableDriversRequest request) {
        GetAvailableDriversResponse response = new GetAvailableDriversResponse();
        response.setDrivers(driversRepository.findAll()
                .stream()
                .filter(driver -> driver.isAvailableFor(request.getRequestedAvailability()))
                .toList());
        return response;
    }
}
