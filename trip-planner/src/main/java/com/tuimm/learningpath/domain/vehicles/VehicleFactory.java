package com.tuimm.learningpath.domain.vehicles;

import java.util.function.Consumer;

public interface VehicleFactory {
    Bike createBike(Consumer<Bike.Builder> builderDirector);

    Car createCar(Consumer<Car.Builder> builderDirector);

    Pullman createPullman(Consumer<Pullman.Builder> builderDirector);

    Scooter createScooter(Consumer<Scooter.Builder> builderDirector);
}
