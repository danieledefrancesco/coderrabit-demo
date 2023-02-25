package com.tuimm.learningpath.drivers.commands;

import com.tuimm.learningpath.mediator.EventHandler;
import com.tuimm.learningpath.trips.OnTripCreatedEvent;
import com.tuimm.learningpath.trips.StagePlan;
import org.springframework.stereotype.Service;

@Service
public class FreeDriversReservationsCommand extends EventHandler<OnTripCreatedEvent> {
    public FreeDriversReservationsCommand() {
        super(OnTripCreatedEvent.class);
    }

    @Override
    public void handle(OnTripCreatedEvent event) {
        event.getTrip().getPlan().getStages().forEach(this::updateDriverReservedSlot);
    }

    private void updateDriverReservedSlot(StagePlan stagePlan) {
        stagePlan.getDriver().freeSlot(stagePlan.getTimeSlot());
        stagePlan.getDriver().save();
    }
}
