package com.tuimm.learningpath.trips.dtos;

import com.tuimm.learningpath.trips.PlanByCheaper;
import com.tuimm.learningpath.trips.PlanByFaster;
import com.tuimm.learningpath.trips.PlanByLessPolluting;
import com.tuimm.learningpath.trips.StagePlan;

import java.util.Comparator;

public enum PreferredPlanPolicy {
    CHEAPEST {
        @Override
        public Comparator<StagePlan> getPolicy() {
            return PlanByCheaper.getInstance();
        }
    }, FASTEST {
        @Override
        public Comparator<StagePlan> getPolicy() {
            return PlanByFaster.getInstance();
        }
    }, LEAST_POLLUTING {
        @Override
        public Comparator<StagePlan> getPolicy() {
            return PlanByLessPolluting.getInstance();
        }
    };
    public abstract Comparator<StagePlan> getPolicy();
}
