package com.tuimm.learningpath.domain.trips;

import com.tuimm.learningpath.common.validators.ObjectValidator;
import com.tuimm.learningpath.common.validators.StringValidator;
import lombok.Getter;

import java.util.Comparator;

@Getter
public class StageDefinition {
    public static StageDefinition create(String from, String to, Comparator<StagePlan> preferredPlanPolicy) {
        return new StageDefinition(from, to, preferredPlanPolicy);
    }
    private final String from;
    private final String to;
    private final Comparator<StagePlan> preferredPlanPolicy;

    private StageDefinition(String from, String to, Comparator<StagePlan> preferredPlanPolicy) {
        this.from = StringValidator.create("from", from).ensureNotNull().ensureNotBlank().getValue();
        this.to = StringValidator.create("to", to).ensureNotNull().ensureNotBlank().getValue();
        this.preferredPlanPolicy = ObjectValidator.create("preferredPlanPolicy", preferredPlanPolicy).ensureNotNull().getValue();
    }
}
