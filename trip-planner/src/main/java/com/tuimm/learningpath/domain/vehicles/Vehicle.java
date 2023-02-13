package com.tuimm.learningpath.domain.vehicles;
import com.tuimm.learningpath.common.validators.NumberValidator;
import com.tuimm.learningpath.common.validators.StringValidator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

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

    protected Vehicle(UUID id, String model, int maxPeople, double dailyRentPrice, double averageSpeed, double autonomy) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        this.id = id;
        this.model = StringValidator.create("model", model).ensureNotNull().ensureNotBlank().getValue();
        this.maxPeople = NumberValidator.create("maxPeople", maxPeople).ensureGreaterThen(0).getValue();
        this.dailyRentPrice = NumberValidator.create("dailyRentPrice", dailyRentPrice).ensureGreaterThenOrEqualTo(0d).getValue();
        this.averageSpeed = NumberValidator.create("averageSpeed", averageSpeed).ensureGreaterThen(0d).getValue();
        this.autonomy = NumberValidator.create("autonomy", autonomy).ensureGreaterThen(0d).getValue();
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
    public abstract DrivingProfile getDrivingProfile();
    public abstract boolean hasCoverage();
    public abstract double getEmissions();

    @Override
    public String toString() {
        return String.format("%s:%s", this.getClass().getSimpleName(), System.lineSeparator()) +
                String.format("  id: %s%s", this.getId(), System.lineSeparator()) +
                String.format("  model: %s%s", this.getModel(), System.lineSeparator()) +
                String.format("  maxPeople: %d%s", this.getMaxPeople(), System.lineSeparator()) +
                String.format("  dailyRentPrice: %f EUR/d%s", this.getDailyRentPrice(), System.lineSeparator()) +
                String.format("  averageSpeed: %f km/h%s", this.getAverageSpeed(), System.lineSeparator()) +
                String.format("  autonomy: %f km%s", this.getAutonomy(), System.lineSeparator()) +
                String.format("  stopTimeInSeconds: %d", this.getStopTimeInSeconds());
    }
}
