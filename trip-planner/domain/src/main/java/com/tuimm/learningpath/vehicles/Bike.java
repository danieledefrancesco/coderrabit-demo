package com.tuimm.learningpath.vehicles;

public class Bike extends Vehicle {
    private static final DrivingPolicy DRIVING_POLICY = DrivingPolicy.builder()
            .suitableForBadWeather(false)
            .minimumDrivingAge(0)
            .drivingProfile(DrivingProfile.BIKE_PROFILE)
            .build();

    private Bike(Builder builder) {
        super(builder);
    }

    @Override
    double getAverageSpeedReductionFactor() {
        return 0.3;
    }

    @Override
    public int getStopTimeInSeconds() {
        return 8 * 60 * 60;
    }

    @Override
    public double getEmissions() {
        return 0;
    }

    @Override
    public DrivingPolicy getDrivingPolicy() {
        return Bike.DRIVING_POLICY;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends Vehicle.Builder<Bike> {
        private Builder() {

        }
        @Override
        public Bike build() {
            return new Bike(this);
        }
    }
}
