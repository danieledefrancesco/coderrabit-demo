package com.tuimm.learningpath.trips;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
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
}