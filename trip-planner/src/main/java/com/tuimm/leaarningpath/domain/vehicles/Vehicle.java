package com.tuimm.leaarningpath.domain.vehicles;
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
        if (model == null) {
            throw new IllegalArgumentException("model cannot be null");
        }
        if (maxPeople <= 0) {
            throw new IllegalArgumentException("maxPeople must be greater than 0");
        }
        if (dailyRentPrice < 0) {
            throw new IllegalArgumentException("dailyRentPrice must be greater than or equal to 0");
        }
        if (averageSpeed <= 0) {
            throw new IllegalArgumentException("averageSpeed must be greater than 0");
        }
        if (autonomy <= 0) {
            throw new IllegalArgumentException("autonomy must be greater than 0");
        }
        this.id = id;
        this.model = model;
        this.maxPeople = maxPeople;
        this.dailyRentPrice = dailyRentPrice;
        this.averageSpeed = averageSpeed;
        this.autonomy = autonomy;
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
