package com.tuimm.learningpath.drivers.commands;

import com.tuimm.learningpath.mediator.Request;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DeleteDriverRequest implements Request<Void> {
    private UUID driverId;
}
