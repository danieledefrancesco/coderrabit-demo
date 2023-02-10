package com.tuimm.learningpath.domain.trips;

import java.util.Comparator;

public class PlanByFaster implements Comparator<StagePlan> {
    private static final PlanByFaster instance = new PlanByFaster();
    public static PlanByFaster getInstance() {
        return instance;
    }

    private PlanByFaster() {

    }
    @Override
    public int compare(StagePlan o1, StagePlan o2) {

        return o1.getDuration().compareTo(o2.getDuration());
    }
}
