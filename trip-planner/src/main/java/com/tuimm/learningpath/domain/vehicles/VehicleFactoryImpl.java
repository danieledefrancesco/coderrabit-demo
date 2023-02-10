package com.tuimm.learningpath.domain.vehicles;

import com.tuimm.learningpath.common.RandomIdGenerator;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VehicleFactoryImpl implements VehicleFactory {
    @NonNull
    private final RandomIdGenerator randomIdGenerator;

    @Override
    public Bike createBike(String model,
                           int maxPeople,
                           double dailyRentPrice,
                           double averageSpeed,
                           double autonomy) {
        return new Bike(randomIdGenerator.generateRandomId(),
                model,
                maxPeople,
                dailyRentPrice,
                averageSpeed,
                autonomy);
    }

    @Override
    public Car createCar(String model,
                         int maxPeople,
                         double dailyRentPrice,
                         double averageSpeed,
                         double autonomy,
                         int stopTimeInSeconds,
                         String plate,
                         FuelType fuelType,
                         double emissions,
                         double fuelConsumption) {
        return new Car(randomIdGenerator.generateRandomId(),
                model,
                maxPeople,
                dailyRentPrice,
                averageSpeed,
                autonomy,
                stopTimeInSeconds,
                GenericPlate.from(plate),
                fuelType,
                emissions,
                fuelConsumption);
    }

    @Override
    public Pullman createPullman(String model,
                                 int maxPeople,
                                 double dailyRentPrice,
                                 double averageSpeed,
                                 double autonomy,
                                 int stopTimeInSeconds,
                                 String plate,
                                 FuelType fuelType,
                                 double emissions,
                                 double fuelConsumption) {
        return new Pullman(randomIdGenerator.generateRandomId(),
                model,
                maxPeople,
                dailyRentPrice,
                averageSpeed,
                autonomy,
                stopTimeInSeconds,
                GenericPlate.from(plate),
                fuelType,
                emissions,
                fuelConsumption);
    }

    @Override
    public Scooter createScooter(String model,
                                 int maxPeople,
                                 double dailyRentPrice,
                                 double averageSpeed,
                                 double autonomy,
                                 int stopTimeInSeconds,
                                 String plate,
                                 FuelType fuelType,
                                 double emissions,
                                 double fuelConsumption) {
        return new Scooter(randomIdGenerator.generateRandomId(),
                model,
                maxPeople,
                dailyRentPrice,
                averageSpeed,
                autonomy,
                stopTimeInSeconds,
                ScooterPlate.from(plate),
                fuelType,
                emissions,
                fuelConsumption);
    }

}
