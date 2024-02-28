package com.tuimm.learningpath.drivers.queries;

import com.tuimm.learningpath.common.TimeSlot;
import com.tuimm.learningpath.mediator.Request;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
@Getter
public class GetAvailableDriversRequest implements Request<GetAvailableDriversResponse> {
    private final TimeSlot requestedAvailability;
}
