package com.tuimm.learningpath.vehicles.queries;

import com.tuimm.learningpath.mediator.RequestHandler;
import com.tuimm.learningpath.vehicles.FuelType;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class GetAllFuelTypesRequestHandler extends RequestHandler<GetAllFuelTypesRequest, GetAllFuelTypesResponse> {
    public GetAllFuelTypesRequestHandler() {
        super(GetAllFuelTypesRequest.class);
    }

    @Override
    public GetAllFuelTypesResponse handle(GetAllFuelTypesRequest request) {
        return GetAllFuelTypesResponse.create(Arrays.asList(FuelType.values()));
    }
}
