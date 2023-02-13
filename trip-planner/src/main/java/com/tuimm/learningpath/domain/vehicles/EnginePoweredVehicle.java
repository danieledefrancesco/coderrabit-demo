package com.tuimm.learningpath.domain.vehicles;

import com.tuimm.learningpath.common.validators.NumberValidator;
import com.tuimm.learningpath.common.validators.ObjectValidator;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public abstract class EnginePoweredVehicle extends Vehicle {
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
        this.stopTimeInSeconds = NumberValidator.create("stopTimeInSeconds", stopTimeInSeconds).ensureGreaterThenOrEqualTo(0).getValue();
        this.plate = ObjectValidator.create("plate", plate).ensureNotNull().getValue();
        this.fuelType = ObjectValidator.create("fuelType", fuelType).ensureNotNull().getValue();
        this.emissions = NumberValidator.create("emissions", emissions).ensureGreaterThenOrEqualTo(0d).getValue();
        this.fuelConsumption = NumberValidator.create("fuelConsumption", fuelConsumption).ensureGreaterThenOrEqualTo(0d).getValue();
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
