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
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    @Then("a driver should be present in the database with that id")
    public void aDriverShouldBePresentInTheDatabaseWithThatId() {
        UUID id = scenarioContext.get(UUID.class);
        DriverEntity entity = driversDao.findById(id).orElse(null);
        Assertions.assertNotNull(entity);
    }


    @Then("the driver with id {word} should no longer be present in the database")
    public void theDriverWithIdShouldNoLongerBePresentInTheDatabase(String idAsString) {
        UUID id = UUID.fromString(idAsString);
        DriverEntity entity = driversDao.findById(id).orElse(null);
        Assertions.assertNull(entity);
    }

    private static DriverEntity toDriverEntity(Map<String, String> map) {
        DrivingLicenseEntity drivingLicense = new DrivingLicenseEntity();
        DriverEntity driverEntity = new DriverEntity();
        driverEntity.setDrivingLicense(drivingLicense);
        driverEntity.setId(UUID.fromString(map.get("id")));
        driverEntity.setFirstName(map.get("firstName"));
        driverEntity.setLastName(map.get("lastName"));
        driverEntity.setCitizenship(map.get("citizenship"));
        driverEntity.setDateOfBirth(LocalDate.parse(map.get("dateOfBirth")));
        drivingLicense.setCode(map.get("licenseCode"));
        drivingLicense.setExpiryDate(LocalDate.parse(map.get("licenseExpiryDate")));
        return driverEntity;
    }

    private static CreateDriverRequestDto toCreateDriverRequest(Map<String, String> map) {
        CreateDrivingLicenseRequestDto drivingLicense = new CreateDrivingLicenseRequestDto();
        CreateDriverRequestDto createDriverRequest = new CreateDriverRequestDto();
        createDriverRequest.setDrivingLicense(drivingLicense);
        createDriverRequest.setFirstName(map.get("firstName"));
        createDriverRequest.setLastName(map.get("lastName"));
        createDriverRequest.setCitizenship(map.get("citizenship"));
        createDriverRequest.setDateOfBirth(LocalDate.parse(map.get("dateOfBirth")));
        drivingLicense.setCode(map.get("licenseCode"));
        drivingLicense.setExpiryDate(LocalDate.parse(map.get("licenseExpiryDate")));
        return createDriverRequest;
    }
}
