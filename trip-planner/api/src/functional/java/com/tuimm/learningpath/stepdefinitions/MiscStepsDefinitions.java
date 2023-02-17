package com.tuimm.learningpath.stepdefinitions;

import com.tuimm.learningpath.TodayDateProvider;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.mockito.Mockito.when;

public class MiscStepsDefinitions extends Definition {
    @Autowired
    private TodayDateProvider todayDateProvider;

    @Given("today is {word}")
    public void todayIs(String dateAsString) {
        when(todayDateProvider.getTodayDate()).thenReturn(LocalDate.parse(dateAsString));
    }
}
