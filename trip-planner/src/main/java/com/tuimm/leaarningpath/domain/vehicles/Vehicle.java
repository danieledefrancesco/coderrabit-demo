package com.tuimm.leaarningpath.domain.vehicles;

import java.util.UUID;

public interface Vehicle {
    UUID getId();
    String getModel();
    int getMaxPeople();
    double getDailyRentPrice();
    double getAverageSpeed();
    double getAutonomy();
    int getStopTimeInSeconds();
    /* Since the bike is a possible vehicle candidate for a trip and since the total emissions will always be shown,
     * even if the trip will be fulfilled by a bike, it makes sense to have the computeEmissions() in the vehicle interface.
     * The vehicles which are not powered by an engine will just return 0.
     */
    double getEmissions();

    DrivingProfile getDrivingProfile();
    double computeAverageSpeedForPassengersAmount(int passengersAmount);
    double computePrice(int numberOfDays, double kilometers);




}
