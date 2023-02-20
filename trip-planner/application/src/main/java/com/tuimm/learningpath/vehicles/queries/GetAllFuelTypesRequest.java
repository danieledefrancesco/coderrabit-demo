package com.tuimm.learningpath.vehicles.queries;

import com.tuimm.learningpath.mediator.Request;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "create")
public class GetAllFuelTypesRequest implements Request<GetAllFuelTypesResponse> {
}
