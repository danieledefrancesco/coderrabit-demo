package com.tuimm.learningpath.vehicles;

import com.tuimm.learningpath.validators.ObjectValidator;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Scooter extends EnginePoweredVehicle {

    private final ScooterPlate plate;
    protected Scooter(ScooterBuilder<?,?> builder) {
        super(builder);
        plate = ObjectValidator.create("plate", builder.plate).ensureNotNull().getValue();
    }
    private static final DrivingPolicy DRIVING_POLICY = DrivingPolicy.builder()
            .drivingProfile(DrivingProfile.CAR_PROFILE)
            .minimumDrivingAge(16)
            .suitableForBadWeather(false)
            .build();

    @Override
    protected double getAverageSpeedReductionFactor() {
        return 0.15;
    }

    @Override
    public DrivingPolicy getDrivingPolicy() {
        return Scooter.DRIVING_POLICY;
    }
}
