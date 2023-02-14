package com.tuimm.learningpath.domain.vehicles;

import com.tuimm.learningpath.common.RandomIdGenerator;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class VehicleFactoryImpl implements VehicleFactory {
    @NonNull
    private final RandomIdGenerator randomIdGenerator;

    @Override
    public Bike createBike(Consumer<Bike.Builder> builderDirector) {
        Bike.Builder builder = Bike.builder();
        builderDirector.accept(builder);
        builder.id(randomIdGenerator.generateRandomId());
        return builder.build();
    }

    @Override
    public Car createCar(Consumer<Car.Builder> builderDirector) {
        Car.Builder builder = Car.builder();
        builderDirector.accept(builder);
        builder.id(randomIdGenerator.generateRandomId());
        return builder.build();
    }

    @Override
    public Pullman createPullman(Consumer<Pullman.Builder> builderDirector) {
        Pullman.Builder builder = Pullman.builder();
        builderDirector.accept(builder);
        builder.id(randomIdGenerator.generateRandomId());
        return builder.build();
    }

    @Override
    public Scooter createScooter(Consumer<Scooter.Builder> builderDirector) {
        Scooter.Builder builder = Scooter.builder();
        builderDirector.accept(builder);
        builder.id(randomIdGenerator.generateRandomId());
        return builder.build();
    }
}
