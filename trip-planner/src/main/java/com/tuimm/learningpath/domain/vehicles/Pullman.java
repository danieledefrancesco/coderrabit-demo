package com.tuimm.learningpath.domain.vehicles;

public class Pullman extends EnginePoweredVehicle {
    private static final DrivingPolicy DRIVING_POLICY = DrivingPolicy.builder()
            .suitableForBadWeather(true)
            .minimumDrivingAge(21)
            .drivingProfile(DrivingProfile.HGV_PROFILE)
            .build();
    public Pullman(Builder builder) {
        super(builder);
    }

    @Override
    protected double getAverageSpeedReductionFactor() {
        return 0.03;
    }

    @Override
    public DrivingPolicy getDrivingPolicy() {
        return Pullman.DRIVING_POLICY;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends EnginePoweredVehicle.Builder<Pullman, GenericPlate> {
        private Builder() {

        }
        @Override
        public Pullman build() {
            return new Pullman(this);
        }
    }
}
