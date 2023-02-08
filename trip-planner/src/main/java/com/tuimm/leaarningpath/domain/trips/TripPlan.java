package com.tuimm.leaarningpath.domain.trips;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor(staticName = "create")
@EqualsAndHashCode
public class TripPlan {
    private final List<StagePlan> stages;

    public Duration getTripDuration() {
        long duration = stages.stream()
                .map(StagePlan::getDuration)
                .mapToLong(Duration::toSeconds)
                .sum();
        return Duration.ofSeconds(duration);
    }

    public LocalDateTime getArrivalDateTime() {
        StagePlan lastStage = stages.get(stages.size() - 1);
        return lastStage.getArrivalDateTime();
    }

    public double getTotalPrice() {
        return stages.stream()
                .mapToDouble(StagePlan::getPrice)
                .sum();
    }

    public double getTotalEmissions() {
        return stages.stream()
                .mapToDouble(StagePlan::getEmissions)
                .sum();
    }

    public String toString() {
        return String.format("TripPlan:%s", System.lineSeparator()) +
                String.format(" duration: %d s%s", this.getTripDuration().toSeconds(), System.lineSeparator()) +
                String.format(" arrivalDateTime: %s%s",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss").format(this.getArrivalDateTime()),
                        System.lineSeparator()) +
                String.format(" totalPrice: %f%s", this.getTotalPrice(), System.lineSeparator()) +
                String.format(" totalEmissions: %f CO2/d%s", this.getTotalEmissions(), System.lineSeparator()) +
                String.format(" stages: %s%s", this.stages, System.lineSeparator());
    }
}