package com.tuimm.leaarningpath.domain.vehicles;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class AbstractVehicle implements Vehicle{
    @EqualsAndHashCode.Include
    @NonNull
    private final UUID id;
    @NonNull
    private final String model;
    @Positive
    private final int maxPeople;
    @PositiveOrZero
    private final double dailyRentPrice;
    @Positive
    private final double averageSpeed;
    @Positive
    private final double autonomy;

    protected AbstractVehicle(UUID id, String model, int maxPeople, double dailyRentPrice, double averageSpeed, double autonomy) {
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

    @Override
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

    @Override
    public double computePrice(int numberOfDays, double kilometers) {
        return dailyRentPrice * numberOfDays;
    }

    abstract double getAverageSpeedReductionFactor();

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
