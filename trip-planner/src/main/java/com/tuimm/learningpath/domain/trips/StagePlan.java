package com.tuimm.learningpath.domain.trips;

import com.tuimm.learningpath.domain.routes.Route;
import com.tuimm.learningpath.domain.vehicles.Vehicle;
import com.tuimm.learningpath.domain.weatherconditions.WeatherCondition;
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
        return getDestinationWeatherCondition().requiresCoverage() && !getVehicle().hasCoverage();
    }

    @Override
    public String toString() {
        return String.format("StagePlan:%s", System.lineSeparator()) +
                String.format(" from: %s%s", this.getRoute().getFrom().getName(), System.lineSeparator()) +
                String.format(" to: %s%s", this.getRoute().getTo().getName(), System.lineSeparator()) +
                String.format(" distance: %f km%s", this.getRoute().getDistanceInKilometers(), System.lineSeparator()) +
                String.format(" duration: %d s%s", this.getDuration().toSeconds(), System.lineSeparator()) +
                String.format(" arrivalDateTime: %s%s",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss").format(this.getArrivalDateTime()),
                        System.lineSeparator()) +
                String.format(" totalPrice: %f EUR%s", this.getPrice(), System.lineSeparator()) +
                String.format(" totalEmissions: %f CO2%s", this.getEmissions(), System.lineSeparator()) +
                String.format(" destinationWeatherCondition: %s%s", this.getDestinationWeatherCondition(), System.lineSeparator()) +
                String.format(" warnForWeatherCondition: %s%s", this.warnForWeatherCondition(), System.lineSeparator()) +
                String.format(" vehicle: %s", this.getVehicle());
    }
}
