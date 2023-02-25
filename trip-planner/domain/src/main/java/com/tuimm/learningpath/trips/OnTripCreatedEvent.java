package com.tuimm.learningpath.trips;

import com.tuimm.learningpath.common.Event;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
@Getter
public class OnTripCreatedEvent implements Event {
    private final Trip trip;
}
