package com.tuimm.learningpath.drivers;

import com.tuimm.learningpath.TodayDateProvider;
import com.tuimm.learningpath.common.Aggregate;
import com.tuimm.learningpath.common.TimeSlot;
import com.tuimm.learningpath.vehicles.Vehicle;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @Setter
    private FirstName firstName;
    @NonNull
    @Setter
    private LastName lastName;
    @NonNull
    @Setter
    private LocalDate dateOfBirth;
    @Setter
    private DrivingLicense drivingLicense;
    @NonNull
    @Setter
    private Citizenship citizenship;
    @NonNull
    private final TodayDateProvider todayDateProvider;
    @NonNull
    private final Collection<TimeSlot> reservedTimeSlots;

    public boolean canDrive(Vehicle vehicle) {
        if (!vehicle.getDrivingPolicy().requiresDrivingLicense()) return true;
        return todayDateProvider.getTodayDate().minusYears(vehicle.getDrivingPolicy().getMinimumDrivingAge()).isAfter(dateOfBirth);
    }

    public boolean canDriveVehicleUntil(Vehicle vehicle, LocalDateTime until) {
        if (!vehicle.getDrivingPolicy().requiresDrivingLicense()) return true;
        if (!todayDateProvider.getTodayDate().minusYears(vehicle.getDrivingPolicy().getMinimumDrivingAge()).isAfter(dateOfBirth)) return false;
        return drivingLicense.getExpiryDate().isAfter(until.toLocalDate());
    }

    public boolean isAvailableFor(TimeSlot timeSlot) {
        return reservedTimeSlots.stream().noneMatch(reservedSlot -> reservedSlot.clashesWith(timeSlot));
    }

    public void reserveSlot(TimeSlot timeSlot) {
        if (!isAvailableFor(timeSlot)) {
            throw new IllegalArgumentException("driver not available for slot");
        }
        reservedTimeSlots.add(timeSlot);
    }

    public void freeSlot(TimeSlot timeSlot) {
        reservedTimeSlots.remove(timeSlot);
    }
}
