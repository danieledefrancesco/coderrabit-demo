package com.tuimm.learningpath.stepdefinitions;

import com.tuimm.learningpath.Driver;
import com.tuimm.learningpath.domain.vehicles.Garage;
import io.cucumber.java.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;

public class Hooks extends Definition {
    @LocalServerPort
    private int port;
    @Autowired
    private Garage garage;

    @Before
    public void initScenarioContext() {
        scenarioContext.cleanUp();
        scenarioContext.set(Driver.class, new Driver(port));
    }

    @Before
    public void clearVehicles() {
        garage.getAllVehicles().forEach(vehicle -> garage.delete(vehicle.getId()));
    }
}
