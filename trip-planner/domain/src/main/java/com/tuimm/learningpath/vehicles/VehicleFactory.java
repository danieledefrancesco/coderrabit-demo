package com.tuimm.learningpath.vehicles;

import java.util.function.Consumer;

public interface VehicleFactory {
    Bike createBike(Consumer<Bike.BikeBuilder<?, ?>> builderDirector);

    Car createCar(Consumer<Car.CarBuilder<?, ?>> builderDirector);

    Pullman createPullman(Consumer<Pullman.PullmanBuilder<?, ?>> builderDirector);

    Scooter createScooter(Consumer<Scooter.ScooterBuilder<?, ?>> builderDirector);
}
