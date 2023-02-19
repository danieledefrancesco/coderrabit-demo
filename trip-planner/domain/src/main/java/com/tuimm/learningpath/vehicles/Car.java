package com.tuimm.learningpath.vehicles;

import com.tuimm.learningpath.validators.ObjectValidator;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Car extends EnginePoweredVehicle {
    private final GenericPlate plate;
    protected Car(CarBuilder<?,?> builder) {
        super(builder);
        plate = ObjectValidator.create("plate", builder.plate).ensureNotNull().getValue();
    }
    private static final DrivingPolicy DRIVING_POLICY = DrivingPolicy.builder()
            .drivingProfile(DrivingProfile.CAR_PROFILE)
            .minimumDrivingAge(18)
            .suitableForBadWeather(true)
            .build();

    @Override
    protected double getAverageSpeedReductionFactor() {
        return 0.1;
    }

    @Override
    public DrivingPolicy getDrivingPolicy() {
        return Car.DRIVING_POLICY;
    }
}
