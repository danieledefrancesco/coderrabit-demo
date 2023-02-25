package com.tuimm.learningpath.stepdefinitions.drivers;

import com.fasterxml.jackson.databind.JsonNode;
import com.tuimm.learningpath.drivers.dal.DriverEntity;
import com.tuimm.learningpath.drivers.dal.DriversDao;
import com.tuimm.learningpath.drivers.dal.DrivingLicenseEntity;
import com.tuimm.learningpath.drivers.dtos.CreateDriverRequestDto;
import com.tuimm.learningpath.drivers.dtos.CreateDrivingLicenseRequestDto;
import com.tuimm.learningpath.stepdefinitions.AssertionUtils;
import com.tuimm.learningpath.stepdefinitions.Definition;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.*;

public class DriversStepsDefinition extends Definition {
    @Autowired
    private DriversDao driversDao;

    @Given("the existing drivers")
    public void givenTheExistingDrivers(DataTable table) {
        table.asMaps(String.class, String.class)
                .stream()
                .map(DriversStepsDefinition::toDriverEntity)
                .forEach(driversDao::save);
    }

    @Given("a create driver request")
    public void givenACreateDriverRequest(DataTable table) {
        table.asMaps(String.class, String.class)
                .stream()
                .findFirst()
                .ifPresent(map -> scenarioContext.getDriver().setRequestBody(toCreateDriverRequest(map)));
    }

    @Then("the response should contain the following drivers")
    public void theResponseShouldContainTheFollowingDrivers(DataTable table) {
        List<Map<String, String>> maps = table.asMaps(String.class, String.class);
        JsonNode drivers = scenarioContext.getDriver().getLastResponseAs(JsonNode.class).get("drivers");
        Assertions.assertEquals(maps.size(), drivers.size());
        for (int i = 0; i < maps.size(); i++) {
            AssertionUtils.assertMapMatchesJson(maps.get(i), drivers.get(i));
        }
    }

    @Then("the response should contain the following driver")
    public void theResponseShouldContainTheFollowingDriver(DataTable table) {
        Map<String, String> map = table.asMaps(String.class, String.class).get(0);
        JsonNode driver = scenarioContext.getDriver().getLastResponseAs(JsonNode.class);
        AssertionUtils.assertMapMatchesJson(map, driver);
    }

    @Then("the response should contain the new driver's id in the location header")
    public void theResponseShouldContainTheNewDriversIdInTheLocationHeader() {
        String locationHeader = scenarioContext.getDriver().getLastResponse()
                .headers().firstValue("Location").orElseThrow();
        String baseUriAndPath = String.format("%s%s",
                scenarioContext.getDriver().getBaseUri(),
                "/drivers");

        Assertions.assertEquals(0, locationHeader.indexOf(baseUriAndPath));
        String vehicleIdAsString = locationHeader.substring(baseUriAndPath.length() + 1);
        UUID id = Assertions.assertDoesNotThrow(() -> UUID.fromString(vehicleIdAsString));
        scenarioContext.set(UUID.class, id);
    }

    @Then("a driver should be present in the database with that id and the following properties")
    public void aDriverShouldBePresentInTheDatabaseWithThatIdAndTheFollowingProperties(DataTable table) {
        Map<String, String> map = table.asMaps().get(0);
        UUID id = scenarioContext.get(UUID.class);
        DriverEntity entity = driversDao.findById(id).orElse(null);
        Assertions.assertNotNull(entity);
        Assertions.assertEquals(map.get("firstName"), entity.getFirstName());
        Assertions.assertEquals(map.get("lastName"), entity.getLastName());
        Assertions.assertEquals(LocalDate.parse(map.get("dateOfBirth")), entity.getDateOfBirth());
        Assertions.assertEquals(map.get("citizenship"), entity.getCitizenship());
        if (map.get("licenseCode").equals("null")) {
            Assertions.assertNull(entity.getDrivingLicense());
        } else {
            Assertions.assertEquals(map.get("licenseCode"), entity.getDrivingLicense().getCode());
            Assertions.assertEquals(LocalDate.parse(map.get("licenseExpiryDate")), entity.getDrivingLicense().getExpiryDate());
        }
    }


    @Then("the driver with id {word} should no longer be present in the database")
    public void theDriverWithIdShouldNoLongerBePresentInTheDatabase(String idAsString) {
        UUID id = UUID.fromString(idAsString);
        DriverEntity entity = driversDao.findById(id).orElse(null);
        Assertions.assertNull(entity);
    }

    private static DriverEntity toDriverEntity(Map<String, String> map) {
        DriverEntity driverEntity = new DriverEntity();
        driverEntity.setId(UUID.fromString(map.get("id")));
        driverEntity.setFirstName(map.get("firstName"));
        driverEntity.setLastName(map.get("lastName"));
        driverEntity.setCitizenship(map.get("citizenship"));
        driverEntity.setDateOfBirth(LocalDate.parse(map.get("dateOfBirth")));
        driverEntity.setReservedTimeSlots(Collections.emptySet());
        if (!map.get("licenseCode").equals("null")) {
            DrivingLicenseEntity drivingLicense = new DrivingLicenseEntity();
            driverEntity.setDrivingLicense(drivingLicense);
            drivingLicense.setCode(map.get("licenseCode"));
            drivingLicense.setExpiryDate(LocalDate.parse(map.get("licenseExpiryDate")));
        }
        return driverEntity;
    }

    private static CreateDriverRequestDto toCreateDriverRequest(Map<String, String> map) {
        CreateDriverRequestDto createDriverRequest = new CreateDriverRequestDto();
        createDriverRequest.setFirstName(map.get("firstName"));
        createDriverRequest.setLastName(map.get("lastName"));
        createDriverRequest.setCitizenship(map.get("citizenship"));
        if (map.get("dateOfBirth") != null && !map.get("dateOfBirth").isBlank()) {
            createDriverRequest.setDateOfBirth(LocalDate.parse(map.get("dateOfBirth")));
        }
        if (!map.get("licenseCode").equals("null")) {
            CreateDrivingLicenseRequestDto drivingLicense = new CreateDrivingLicenseRequestDto();
            createDriverRequest.setDrivingLicense(drivingLicense);
            drivingLicense.setCode(map.get("licenseCode"));
            drivingLicense.setExpiryDate(LocalDate.parse(map.get("licenseExpiryDate")));
        }
        return createDriverRequest;
    }

}
