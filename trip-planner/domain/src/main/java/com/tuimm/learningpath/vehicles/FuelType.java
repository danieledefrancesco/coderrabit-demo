package com.tuimm.learningpath.vehicles;

public enum FuelType {
    PETROL, DIESEL, LPG;
    private double cost;
    public double getCost() {
        return cost;
    }
    public void setCost(double cost) {
        if (cost <= 0) {
            throw new IllegalArgumentException("cost must be greater than 0");
        }
        this.cost = cost;
    }
    public String getName() {
        return name();
    }
}
