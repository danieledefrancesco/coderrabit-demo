package com.tuimm.learningpath.drivers.queries;

import com.tuimm.learningpath.drivers.Driver;
import com.tuimm.learningpath.mediator.Request;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor(staticName = "fromId")
public class GetDriverByIdRequest implements Request<Driver> {
    private final UUID driverId;
}
