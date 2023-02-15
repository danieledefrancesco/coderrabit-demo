package com.tuimm.learningpath;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TodayDateProviderImpl implements TodayDateProvider {
    @Override
    public LocalDate getTodayDate() {
        return LocalDate.now();
    }
}
