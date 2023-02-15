package com.tuimm.learningpath.vehicles;

public class Car extends EnginePoweredVehicle {
    private static final DrivingPolicy DRIVING_POLICY = DrivingPolicy.builder()
            .drivingProfile(DrivingProfile.CAR_PROFILE)
            .minimumDrivingAge(18)
            .suitableForBadWeather(true)
            .build();

    public Car(Builder builder) {
        super(builder);
    }

    @Override
    protected double getAverageSpeedReductionFactor() {
        return 0.1;
    }

    @Override
    public DrivingPolicy getDrivingPolicy() {
        return Car.DRIVING_POLICY;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends EnginePoweredVehicle.Builder<Car, GenericPlate> {
        private Builder() {

        }
        @Override
        public Car build() {
            return new Car(this);
        }
    }
}
