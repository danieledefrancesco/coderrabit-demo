package com.tuimm.learningpath.domain.vehicles;

public interface VehicleVisitor<T> {
    T visit(Bike bike);
    T visit(Car car);
    T visit(Scooter scooter);
    T visit(Pullman pullman);
}
