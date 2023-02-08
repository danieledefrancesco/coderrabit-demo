package com.tuimm.leaarningpath.domain.trips;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PlanByFasterTest {
    private final PlanByFaster comparator = PlanByFaster.getInstance();

    @Test
    void compare_shouldReturnZero_whenPlansHaveSameDuration() {
        Assertions.assertEquals(0, comparator.compare(
                createStagePlanMock(1),
                createStagePlanMock(1)
        ));
    }

    @Test
    void compare_shouldReturnMinusOne_whenFirstPlanIsFasterThanTheSecond() {
        Assertions.assertEquals(-1, comparator.compare(
                createStagePlanMock(1),
                createStagePlanMock(2)
        ));
    }

    @Test
    void compare_shouldReturnOne_whenFirstPlanIsSlowerThanTheSecond() {
        Assertions.assertEquals(1, comparator.compare(
                createStagePlanMock(2),
                createStagePlanMock(1)
        ));
    }

    private StagePlan createStagePlanMock(int durationInSeconds) {
        StagePlan mock = mock(StagePlan.class);
        when(mock.getDuration()).thenReturn(Duration.ofSeconds(durationInSeconds));
        return mock;
    }

}
