package com.tuimm.learningpath.vehicles;

import lombok.NonNull;

public class ScooterPlate extends Plate {
    public static ScooterPlate from(@NonNull String value)
    {
        return new ScooterPlate(value);
    }
    private ScooterPlate(String value) {
        super(value);
    }

    @Override
    protected String getPlateRegex() {
        return "[A-Z]{2}[0-9]{4}";
    }
}
