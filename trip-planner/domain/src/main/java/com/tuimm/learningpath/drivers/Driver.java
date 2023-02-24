package com.tuimm.learningpath.drivers;

import com.tuimm.learningpath.TodayDateProvider;
import com.tuimm.learningpath.common.Aggregate;
import com.tuimm.learningpath.vehicles.Vehicle;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

@Getter
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Driver extends Aggregate<Driver> {
    @EqualsAndHashCode.Include
    @NonNull
    private final UUID id;
    @NonNull
    private final FirstName firstName;
    @NonNull
    private final LastName lastName;
    @NonNull
    private final LocalDate dateOfBirth;
    private final DrivingLicense drivingLicense;
    @NonNull
    private final Citizenship citizenship;
    @NonNull
    private final TodayDateProvider todayDateProvider;
    @NonNull
    private final Collection<Slot> reservedSlots;

    public boolean canDrive(Vehicle vehicle) {
        if (!vehicle.getDrivingPolicy().requiresDrivingLicense()) return true;
        return todayDateProvider.getTodayDate().minusYears(vehicle.getDrivingPolicy().getMinimumDrivingAge()).isAfter(dateOfBirth);
    }

    public boolean isAvailableFor(Slot slot) {
        return reservedSlots.stream().noneMatch(reservedSlot -> reservedSlot.clashesWith(slot));
    }

    public void reserveSlot(Slot slot) {
        if (!isAvailableFor(slot)) {
            throw new IllegalArgumentException("driver not available for slot");
        }
        reservedSlots.add(slot);
    }
}
