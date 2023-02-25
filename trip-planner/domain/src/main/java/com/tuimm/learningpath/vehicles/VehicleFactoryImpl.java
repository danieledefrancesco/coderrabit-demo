package com.tuimm.learningpath.vehicles;

import com.tuimm.learningpath.RandomIdGenerator;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class VehicleFactoryImpl implements VehicleFactory {
    @NonNull
    private final RandomIdGenerator randomIdGenerator;
    @NonNull
    private final VehicleAggregateManager aggregateManager;

    @Override
    public Bike createBike(Consumer<Bike.BikeBuilder<?, ?>> builderDirector) {
        Bike.BikeBuilder<?, ?> builder = Bike.builder();
        builderDirector.accept(builder);
        return builder.id(randomIdGenerator.generateRandomId())
                .aggregateManager(aggregateManager)
                .reservedTimeSlots(Collections.emptyList())
                .build();
    }

    @Override
    public Car createCar(Consumer<Car.CarBuilder<?, ?>> builderDirector) {
        Car.CarBuilder<?, ?> builder = Car.builder();
        builderDirector.accept(builder);
        return builder.id(randomIdGenerator.generateRandomId())
                .aggregateManager(aggregateManager)
                .reservedTimeSlots(Collections.emptyList())
                .build();
    }

    @Override
    public Pullman createPullman(Consumer<Pullman.PullmanBuilder<?, ?>> builderDirector) {
        Pullman.PullmanBuilder<?, ?> builder = Pullman.builder();
        builderDirector.accept(builder);
        return builder.id(randomIdGenerator.generateRandomId())
                .aggregateManager(aggregateManager)
                .reservedTimeSlots(Collections.emptyList())
                .build();
    }

    @Override
    public Scooter createScooter(Consumer<Scooter.ScooterBuilder<?, ?>> builderDirector) {
        Scooter.ScooterBuilder<?, ?> builder = Scooter.builder();
        builderDirector.accept(builder);
        return builder.id(randomIdGenerator.generateRandomId())
                .aggregateManager(aggregateManager)
                .reservedTimeSlots(Collections.emptyList())
                .build();
    }
}
