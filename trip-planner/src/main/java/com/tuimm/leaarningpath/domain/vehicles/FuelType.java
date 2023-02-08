package com.tuimm.leaarningpath.domain.vehicles;

public enum FuelType {
    PETROL, DIESEL, LPG;
    private double cost;
    public double getCost() {
        return cost;
    }
    void setCost(double cost) {
        if (cost <= 0) {
            throw new IllegalArgumentException("cost must be greater than 0");
        }
        this.cost = cost;
    }
    @Override
    public String toString()
    {
        return String.format("%s - %f EUR/l", this.name(), this.getCost());
    }
}
