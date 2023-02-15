package com.tuimm.learningpath.trips;

import java.util.Comparator;

public class PlanByCheaper implements Comparator<StagePlan> {
    private static final PlanByCheaper instance = new PlanByCheaper();

    public static PlanByCheaper getInstance() {
        return instance;
    }

    private PlanByCheaper() {

    }

    @Override
    public int compare(StagePlan o1, StagePlan o2) {
        if (o1.getPrice() == o2.getPrice()) return 0;
        return o1.getPrice() < o2.getPrice() ? -1 : 1;
    }
}
