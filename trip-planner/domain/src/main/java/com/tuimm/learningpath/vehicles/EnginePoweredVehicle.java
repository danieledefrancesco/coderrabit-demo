package com.tuimm.learningpath.vehicles;

import com.tuimm.learningpath.validators.NumberValidator;
import com.tuimm.learningpath.validators.ObjectValidator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public abstract class EnginePoweredVehicle extends Vehicle {
    private final int stopTimeInSeconds;
    private final Plate plate;
    private final FuelType fuelType;
    private final double emissions;
    private final double fuelConsumption;

    protected EnginePoweredVehicle(Builder<?,?> builder) {
        super(builder);
        this.stopTimeInSeconds = NumberValidator.create("stopTimeInSeconds", builder.stopTimeInSeconds).ensureGreaterThenOrEqualTo(0).getValue();
        this.plate = ObjectValidator.create("plate", builder.plate).ensureNotNull().getValue();
        this.fuelType = ObjectValidator.create("fuelType", builder.fuelType).ensureNotNull().getValue();
        this.emissions = NumberValidator.create("emissions", builder.emissions).ensureGreaterThenOrEqualTo(0d).getValue();
        this.fuelConsumption = NumberValidator.create("fuelConsumption", builder.fuelConsumption).ensureGreaterThenOrEqualTo(0d).getValue();
    }

    @Override
    public double computePrice(int numberOfDays, double kilometers) {
        double fuelPrice = this.getFuelType().getCost() * (kilometers / this.getFuelConsumption());
        return super.computePrice(numberOfDays, kilometers) + fuelPrice;
    }

    @Getter
    @Setter
    @Accessors(fluent = true)
    public abstract static class Builder<T1 extends Vehicle, T2 extends Plate> extends Vehicle.Builder<T1> {
        private int stopTimeInSeconds;
        private T2 plate;
        private FuelType fuelType;
        private double emissions;
        private double fuelConsumption;
    }
}
