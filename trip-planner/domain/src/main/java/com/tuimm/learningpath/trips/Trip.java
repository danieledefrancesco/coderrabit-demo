package com.tuimm.learningpath.trips;

import com.tuimm.learningpath.TodayDateProvider;
import com.tuimm.learningpath.common.Aggregate;
import com.tuimm.learningpath.common.TimeSlot;
import com.tuimm.learningpath.drivers.Driver;
import com.tuimm.learningpath.exceptions.DriverCannotDriveVehicleException;
import com.tuimm.learningpath.exceptions.DriverNotAvailableException;
import com.tuimm.learningpath.exceptions.EntityNotFoundException;
import com.tuimm.learningpath.exceptions.TripAlreadyStartedException;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@SuperBuilder
public class Trip extends Aggregate<Trip> {
    private final UUID id;
    private final TodayDateProvider todayDateProvider;
    private final TripPlan plan;

    @Override
    public void delete() {
        super.delete();
        addEvent(OnTripDeletedEvent.of(this));
    }

    public void updateStageDriver(TimeSlot stageTimeSlot, Driver newDriver) {
        if (isStarted()) {
            throw new TripAlreadyStartedException();
        }
        if (!newDriver.isAvailableFor(stageTimeSlot)) {
            throw new DriverNotAvailableException(newDriver, stageTimeSlot);
        }
        StagePlan stage = plan.getStages().stream().filter(s -> s.getTimeSlot().equals(stageTimeSlot))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("StagePlan", stageTimeSlot.toString()));
        if (!newDriver.canDrive(stage.getVehicle())) {
            throw new DriverCannotDriveVehicleException(newDriver, stage.getVehicle());
        }
        Driver oldDriver = stage.getDriver();
        stage.setDriver(newDriver);
        addEvent(OnStageDriverUpdated.builder()
                .trip(this)
                .newDriver(newDriver)
                .oldDriver(oldDriver)
                .stagePlan(stage)
                .build());
    }

    public boolean isStarted() {
        return getStartDateTime().isAfter(todayDateProvider.now());
    }

    public LocalDateTime getStartDateTime() {
        return plan.getStages().get(0).getStartDateTime();
    }
}
