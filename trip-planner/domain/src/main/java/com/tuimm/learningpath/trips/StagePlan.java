package com.tuimm.learningpath.trips;

import com.tuimm.learningpath.drivers.Driver;
import com.tuimm.learningpath.routes.Route;
import com.tuimm.learningpath.vehicles.Vehicle;
import com.tuimm.learningpath.weatherconditions.WeatherCondition;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Builder
@Getter
public class StagePlan {
    @NonNull
    private final LocalDateTime startDateTime;
    @NonNull
    private final Route route;
    @NonNull
    private final WeatherCondition destinationWeatherCondition;
    @NonNull
    private final Vehicle vehicle;
    private final int numberOfPeople;

    @NonNull
    private final Driver driver;

    public double getPrice()
    {
        return vehicle.computePrice((int) getDuration().toDays(), route.getDistanceInKilometers());
    }

    public double getEmissions() {
        return vehicle.getEmissions() * route.getDistanceInKilometers();
    }

    public int getRequiredStops() {
        return (int)(route.getDistanceInKilometers() / vehicle.getAutonomy());
    }

    public Duration getDuration()
    {
        long routeDurationInSeconds =
                (int) ((route.getDistanceInKilometers() / vehicle.computeAverageSpeedForPassengersAmount(numberOfPeople)) * 3600);
        int stopsDurationInSeconds = vehicle.getStopTimeInSeconds() * getRequiredStops();
        return Duration.ofSeconds(routeDurationInSeconds + stopsDurationInSeconds);
    }

    public LocalDateTime getArrivalDateTime() {
        return startDateTime.plus(getDuration());
    }

    public boolean warnForWeatherCondition() {
        return getDestinationWeatherCondition().isBadWeather() && !getVehicle().getDrivingPolicy().isSuitableForBadWeather();
    }
}
