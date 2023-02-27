package com.tuimm.learningpath.stepdefinitions;

import com.tuimm.learningpath.TodayDateProvider;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.mockito.Mockito.when;

public class MiscStepsDefinitions extends Definition {
    @Autowired
    private TodayDateProvider todayDateProvider;

    @Given("today is {word}")
    public void todayIs(String dateAsString) {
        LocalDateTime now = LocalDateTime.parse(dateAsString);
        when(todayDateProvider.now()).thenReturn(now);
        when(todayDateProvider.getTodayDate()).thenReturn(now.toLocalDate());
    }
}
