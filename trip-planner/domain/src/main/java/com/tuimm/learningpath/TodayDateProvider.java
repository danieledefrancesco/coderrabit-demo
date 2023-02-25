package com.tuimm.learningpath;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface TodayDateProvider {
    LocalDate getTodayDate();
    LocalDateTime now();
}
