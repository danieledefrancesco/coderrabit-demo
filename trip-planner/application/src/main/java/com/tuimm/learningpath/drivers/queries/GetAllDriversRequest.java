package com.tuimm.learningpath.drivers.queries;
import com.tuimm.learningpath.mediator.Request;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor(staticName = "create")
public class GetAllDriversRequest implements Request<GetAllDriversResponse> {
}
