package com.tuimm.learningpath;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class TodayDateProviderImpl implements TodayDateProvider {
    @Override
    public LocalDate getTodayDate() {
        return LocalDate.now();
    }

    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
