package com.tuimm.learningpath.vehicles.commands;

import com.tuimm.learningpath.mediator.EventHandler;
import com.tuimm.learningpath.trips.OnTripCreatedEvent;
import com.tuimm.learningpath.trips.StagePlan;
import org.springframework.stereotype.Service;

@Service
public class FreeVehiclesReservationsCommand extends EventHandler<OnTripCreatedEvent> {
    public FreeVehiclesReservationsCommand() {
        super(OnTripCreatedEvent.class);
    }

    @Override
    public void handle(OnTripCreatedEvent event) {
        event.getTrip().getPlan().getStages().forEach(this::updateVehicleSlots);
    }

    private void updateVehicleSlots(StagePlan stagePlan) {
        stagePlan.getVehicle().freeSlot(stagePlan.getTimeSlot());
        stagePlan.getVehicle().save();
    }
}
