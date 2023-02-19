package com.tuimm.learningpath.vehicles;

import com.tuimm.learningpath.validators.ObjectValidator;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Pullman extends EnginePoweredVehicle {
    private final GenericPlate plate;
    protected Pullman(PullmanBuilder<?,?> builder) {
        super(builder);
        plate = ObjectValidator.create("plate", builder.plate).ensureNotNull().getValue();
    }
    private static final DrivingPolicy DRIVING_POLICY = DrivingPolicy.builder()
            .suitableForBadWeather(true)
            .minimumDrivingAge(21)
            .drivingProfile(DrivingProfile.HGV_PROFILE)
            .build();

    @Override
    protected double getAverageSpeedReductionFactor() {
        return 0.03;
    }

    @Override
    public DrivingPolicy getDrivingPolicy() {
        return Pullman.DRIVING_POLICY;
    }
}
