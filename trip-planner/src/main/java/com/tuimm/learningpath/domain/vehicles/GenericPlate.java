package com.tuimm.learningpath.domain.vehicles;

import lombok.NonNull;

public class GenericPlate extends Plate {
    public static GenericPlate from(@NonNull String value)
    {
        return new GenericPlate(value);
    }
    private GenericPlate(String value) {
        super(value);
    }

    @Override
    protected String getPlateRegex() {
        return "[A-Z]{2}[0-9]{3}[A-Z]{2}";
    }
}
