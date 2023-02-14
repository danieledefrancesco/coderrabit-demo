package com.tuimm.learningpath.application.vehicles.queries;

import com.tuimm.learningpath.common.mediator.Request;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "create")
public class GetAllVehiclesRequest implements Request<GetVehiclesResponse> {
}
