package com.tuimm.learningpath.domain.vehicles;

public class Scooter extends EnginePoweredVehicle {
    private static final DrivingPolicy DRIVING_POLICY = DrivingPolicy.builder()
            .drivingProfile(DrivingProfile.CAR_PROFILE)
            .minimumDrivingAge(16)
            .suitableForBadWeather(false)
            .build();


    public Scooter(Builder builder) {
        super(builder);
    }

    @Override
    protected double getAverageSpeedReductionFactor() {
        return 0.15;
    }

    @Override
    public DrivingPolicy getDrivingPolicy() {
        return Scooter.DRIVING_POLICY;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends EnginePoweredVehicle.Builder<Scooter, ScooterPlate> {
        private Builder() {

        }
        @Override
        public Scooter build() {
            return new Scooter(this);
        }
    }
}
