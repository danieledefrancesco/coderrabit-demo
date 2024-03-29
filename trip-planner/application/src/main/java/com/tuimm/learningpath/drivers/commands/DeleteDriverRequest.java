package com.tuimm.learningpath.drivers.commands;

import com.tuimm.learningpath.mediator.Request;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor(staticName = "fromId")
public class DeleteDriverRequest implements Request<Void> {
    private final UUID driverId;
}
