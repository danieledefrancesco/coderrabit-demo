package com.tuimm.learningpath.drivers.queries;

import com.tuimm.learningpath.drivers.Driver;
import com.tuimm.learningpath.drivers.DriversRepository;
import com.tuimm.learningpath.mediator.RequestHandler;
import org.springframework.stereotype.Component;

@Component
public class GetDriverByIdRequestHandler extends RequestHandler<GetDriverByIdRequest, Driver> {
    private final DriversRepository repository;
    public GetDriverByIdRequestHandler(DriversRepository repository) {
        super(GetDriverByIdRequest.class);
        this.repository = repository;
    }

    @Override
    public Driver handle(GetDriverByIdRequest request) {
        return repository.findById(request.getDriverId());
    }
}
