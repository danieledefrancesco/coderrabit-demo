package com.tuimm.leaarningpath.domain.trips;

import java.util.Comparator;

public class PlanByLessPolluting implements Comparator<StagePlan> {
    private static final PlanByLessPolluting instance = new PlanByLessPolluting();

    public static PlanByLessPolluting getInstance() {
        return instance;
    }

    private PlanByLessPolluting() {
    }

    @Override
    public int compare(StagePlan o1, StagePlan o2) {
        if (o1.getEmissions() == o2.getEmissions()) {
            return 0;
        }
        return o1.getEmissions() < o2.getEmissions() ? -1 : 1;
    }
}
