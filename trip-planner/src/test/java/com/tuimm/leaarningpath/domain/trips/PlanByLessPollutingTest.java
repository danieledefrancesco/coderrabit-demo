package com.tuimm.leaarningpath.domain.trips;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PlanByLessPollutingTest {
    private final PlanByLessPolluting comparator = PlanByLessPolluting.getInstance();

    @Test
    void compare_shouldReturnZero_whenPlansAreEquallyPolluting() {
        Assertions.assertEquals(0, comparator.compare(
                createStagePlanMock(1),
                createStagePlanMock(1)
        ));
    }

    @Test
    void compare_shouldReturnMinusOne_whenFirstPlanIsLessPollutingThanTheSecond() {
        Assertions.assertEquals(-1, comparator.compare(
                createStagePlanMock(1),
                createStagePlanMock(2)
        ));
    }

    @Test
    void compare_shouldReturnOne_whenFirstPlanIsMorePollutingThanTheSecond() {
        Assertions.assertEquals(1, comparator.compare(
                createStagePlanMock(2),
                createStagePlanMock(1)
        ));
    }

    private StagePlan createStagePlanMock(double emissions) {
        StagePlan mock = mock(StagePlan.class);
        when(mock.getEmissions()).thenReturn(emissions);
        return mock;
    }

}
