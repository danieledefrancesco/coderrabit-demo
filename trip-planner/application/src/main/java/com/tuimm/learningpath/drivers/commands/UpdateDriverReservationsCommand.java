package com.tuimm.learningpath.drivers.commands;

import com.tuimm.learningpath.mediator.EventHandler;
import com.tuimm.learningpath.trips.OnStageDriverUpdated;
import org.springframework.stereotype.Component;

@Component
public class UpdateDriverReservationsCommand extends EventHandler<OnStageDriverUpdated> {
    public UpdateDriverReservationsCommand() {
        super(OnStageDriverUpdated.class);
    }

    @Override
    public void handle(OnStageDriverUpdated event) {
        event.getOldDriver().freeSlot(event.getStagePlan().getTimeSlot());
        event.getOldDriver().save();
        event.getNewDriver().freeSlot(event.getStagePlan().getTimeSlot());
        event.getNewDriver().save();
    }
}
