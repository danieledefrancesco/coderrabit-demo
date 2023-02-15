package com.tuimm.learningpath;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TodayDateProviderImplTest {
    private final TodayDateProviderImpl todayDateProvider = new TodayDateProviderImpl();

    @Test
    void getTodayDate_shouldReturnNotNull() {
        Assertions.assertNotNull(todayDateProvider.getTodayDate());
    }
}
