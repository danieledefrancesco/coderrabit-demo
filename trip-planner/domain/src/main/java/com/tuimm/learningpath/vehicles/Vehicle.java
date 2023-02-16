package com.tuimm.learningpath.vehicles;
import com.tuimm.learningpath.validators.NumberValidator;
import com.tuimm.learningpath.validators.ObjectValidator;
import com.tuimm.learningpath.validators.StringValidator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Vehicle {
    @EqualsAndHashCode.Include
    @NonNull
    private final UUID id;
    @NonNull
    private final String model;
    private final int maxPeople;
    private final double dailyRentPrice;
    private final double averageSpeed;
    private final double autonomy;

    protected Vehicle(Builder<?> builder) {
        this.id = ObjectValidator.create("id", builder.id).ensureNotNull().getValue();
        this.model = StringValidator.create("model", builder.model).ensureNotNull().ensureNotBlank().getValue();
        this.maxPeople = NumberValidator.create("maxPeople", builder.maxPeople).ensureGreaterThen(0).getValue();
        this.dailyRentPrice = NumberValidator.create("dailyRentPrice", builder.dailyRentPrice).ensureGreaterThenOrEqualTo(0d).getValue();
        this.averageSpeed = NumberValidator.create("averageSpeed", builder.averageSpeed).ensureGreaterThen(0d).getValue();
        this.autonomy = NumberValidator.create("autonomy", builder.autonomy).ensureGreaterThen(0d).getValue();
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

    @Accessors(fluent = true)
    @Getter
    @Setter
    public abstract static class Builder<T extends Vehicle> {
        private UUID id;
        private String model;
        private int maxPeople;
        private double dailyRentPrice;
        private double averageSpeed;
        private double autonomy;

        public abstract T build();
    }
}
