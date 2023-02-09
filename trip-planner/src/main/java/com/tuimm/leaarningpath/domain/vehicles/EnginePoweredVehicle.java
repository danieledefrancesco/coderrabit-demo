package com.tuimm.leaarningpath.domain.vehicles;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public abstract class EnginePoweredVehicle extends AbstractVehicle {
    private final int stopTimeInSeconds;
    private final Plate plate;
    private final FuelType fuelType;
    private final double emissions;
    private final double fuelConsumption;

    protected EnginePoweredVehicle(UUID id,
                                   String model,
                                   int maxPeople,
                                   double dailyRentPrice,
                                   double averageSpeed,
                                   double autonomy,
                                   int stopTimeInSeconds,
                                   Plate plate,
                                   FuelType fuelType,
                                   double emissions,
                                   double fuelConsumption) {

        super(id, model, maxPeople, dailyRentPrice, averageSpeed, autonomy);
        if (stopTimeInSeconds < 0) {
            throw new IllegalArgumentException("stopTimeInSeconds must be greater than or equal to 0");
        }
        if (plate == null) {
            throw new IllegalArgumentException("plate cannot be null");
        }
        if (fuelType == null) {
            throw new IllegalArgumentException("fuelType cannot be null");
        }
        if (emissions < 0) {
            throw new IllegalArgumentException("emissions must be greater than or equal to 0");
        }
        if (fuelConsumption < 0) {
            throw new IllegalArgumentException("fuelConsumption must be greater than or equal to 0");
        }
        this.stopTimeInSeconds = stopTimeInSeconds;
        this.plate = plate;
        this.fuelType = fuelType;
        this.emissions = emissions;
        this.fuelConsumption = fuelConsumption;
    }
    @Override
    public double computePrice(int numberOfDays, double kilometers) {
        double fuelPrice = this.getFuelType().getCost() * (kilometers / this.getFuelConsumption());
        return super.computePrice(numberOfDays, kilometers) + fuelPrice;
    }

    @Override
    public String toString() {
        return super.toString() +
                System.lineSeparator() +
                String.format("  plate: %s%s", this.getPlate(), System.lineSeparator()) +
                String.format("  fuelType: %s%s", this.getFuelType(), System.lineSeparator()) +
                String.format("  fuelConsumption: %f km/l%s", this.getFuelConsumption(), System.lineSeparator()) +
                String.format("  emissions: %f CO2/km", this.getEmissions());
    }
}
