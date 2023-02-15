package com.tuimm.learningpath.trips;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PlanByCheaperTest {
    private final PlanByCheaper comparator = PlanByCheaper.getInstance();

    @Test
    void compare_shouldReturnZero_whenPlansHaveSamePrice() {
        Assertions.assertEquals(0, comparator.compare(
                createStagePlanMock(1),
                createStagePlanMock(1)
        ));
    }

    @Test
    void compare_shouldReturnMinusOne_whenFirstPlanIsCheaperThanTheSecond() {
        Assertions.assertEquals(-1, comparator.compare(
                createStagePlanMock(1),
                createStagePlanMock(2)
        ));
    }

    @Test
    void compare_shouldReturnOne_whenFirstPlanIsMoreExpensiveThanTheSecond() {
        Assertions.assertEquals(1, comparator.compare(
                createStagePlanMock(2),
                createStagePlanMock(1)
        ));
    }

    private StagePlan createStagePlanMock(double price) {
        StagePlan mock = mock(StagePlan.class);
        when(mock.getPrice()).thenReturn(price);
        return mock;
    }

}
