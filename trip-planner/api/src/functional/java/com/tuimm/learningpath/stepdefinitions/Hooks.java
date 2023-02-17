package com.tuimm.learningpath.stepdefinitions;

import com.tuimm.learningpath.Driver;
import com.tuimm.learningpath.drivers.dal.DriversDao;
import com.tuimm.learningpath.trips.dal.TripsDao;
import com.tuimm.learningpath.vehicles.Garage;
import com.tuimm.learningpath.vehicles.dal.VehiclesDao;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;

public class Hooks extends Definition {
    @LocalServerPort
    private int port;
    @Autowired
    private VehiclesDao vehiclesDao;
    @Autowired
    private DriversDao driversDao;
    @Autowired
    private TripsDao tripsDao;

    @Before
    public void initScenarioContext() {
        scenarioContext.cleanUp();
        scenarioContext.set(Driver.class, new Driver(port));
    }

    @Before
    @After
    public void clearDatabase() {
        tripsDao.deleteAll();
        vehiclesDao.deleteAll();
        driversDao.deleteAll();
    }
}
