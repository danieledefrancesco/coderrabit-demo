package com.tuimm.learningpath.vehicles;
import com.tuimm.learningpath.common.Aggregate;
import com.tuimm.learningpath.common.TimeSlot;
import com.tuimm.learningpath.validators.CollectionValidator;
import com.tuimm.learningpath.validators.NumberValidator;
import com.tuimm.learningpath.validators.ObjectValidator;
import com.tuimm.learningpath.validators.StringValidator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Collection;
import java.util.UUID;

@SuperBuilder
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public abstract class Vehicle extends Aggregate<Vehicle> {
    @EqualsAndHashCode.Include
    private final UUID id;
    private final String model;
    private final int maxPeople;
    private final double dailyRentPrice;
    private final double averageSpeed;
    private final double autonomy;
    private final Collection<TimeSlot> reservedTimeSlots;

    protected Vehicle(VehicleBuilder<?,?> builder) {
        super(builder);
        this.id = ObjectValidator.create("id", builder.id).ensureNotNull().getValue();
        this.model = StringValidator.create("model", builder.model).ensureNotNull().ensureNotBlank().getValue();
        this.maxPeople = NumberValidator.create("maxPeople", builder.maxPeople).ensureGreaterThen(0).getValue();
        this.dailyRentPrice = NumberValidator.create("dailyRentPrice", builder.dailyRentPrice).ensureGreaterThenOrEqualTo(0d).getValue();
        this.averageSpeed = NumberValidator.create("averageSpeed", builder.averageSpeed).ensureGreaterThen(0d).getValue();
        this.autonomy = NumberValidator.create("autonomy", builder.autonomy).ensureGreaterThen(0d).getValue();
        this.reservedTimeSlots = CollectionValidator.create("reservedTimeSlots", builder.reservedTimeSlots).ensureNotNull().ensureAllNotNull().getValue();
    }

    public double computeAverageSpeedForPassengersAmount(int passengersAmount) {
        if (passengersAmount > this.maxPeople) {
            throw new IllegalArgumentException(
                    String.format(
                            "Error while computing the average speed for %d passengers. The vehicle %s allows at most %d people.",
                            passengersAmount,
                            this.id,
                            this.maxPeople));
        }

        return this.averageSpeed * (1 - getAverageSpeedReductionFactor() * (passengersAmount - 1));
    }

    /**
     * @param kilometers will be used by subclasses
     */
    public double computePrice(int numberOfDays, double kilometers) {
        return dailyRentPrice * numberOfDays;
    }
    abstract double getAverageSpeedReductionFactor();
    public abstract int getStopTimeInSeconds();
    public abstract double getEmissions();
    public abstract DrivingPolicy getDrivingPolicy();
    public boolean isAvailableFor(TimeSlot timeSlot) {
        return reservedTimeSlots.stream().noneMatch(reservedSlot -> reservedSlot.clashesWith(timeSlot));
    }

    public void reserveSlot(TimeSlot timeSlot) {
        if (!isAvailableFor(timeSlot)) {
            throw new IllegalArgumentException("vehicle not available for slot");
        }
        reservedTimeSlots.add(timeSlot);
    }

    public void freeSlot(TimeSlot timeSlot) {
        reservedTimeSlots.remove(timeSlot);
    }
}
