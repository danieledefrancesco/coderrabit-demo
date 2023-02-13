package com.tuimm.learningpath.stepdefinitions.vehicles;

import com.fasterxml.jackson.databind.JsonNode;
import com.tuimm.learningpath.contracts.vehicles.*;
import com.tuimm.learningpath.domain.vehicles.*;
import com.tuimm.learningpath.infrastructure.vehicles.dal.VehicleDao;
import com.tuimm.learningpath.infrastructure.vehicles.dal.VehicleEntity;
import com.tuimm.learningpath.stepdefinitions.Definition;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.UUID;
import java.util.stream.StreamSupport;

public class VehiclesStepsDefinition extends Definition {
    @Autowired
    private Garage garage;
    @Autowired
    private VehicleDao dao;

    @Given("a create bike request")
    public void aCreateBikeRequest(DataTable table) {
        Map<String, String> tableAsMap = table.asMaps(String.class, String.class).get(0);
        CreateBikeRequest createBikeRequest = new CreateBikeRequest();
        createBikeRequest.setModel(tableAsMap.get("model"));
        createBikeRequest.setMaxPeople(Integer.parseInt(tableAsMap.get("maxPeople")));
        createBikeRequest.setDailyRentPrice(Double.parseDouble(tableAsMap.get("dailyRentPrice")));
        createBikeRequest.setAutonomy(Double.parseDouble(tableAsMap.get("autonomy")));
        createBikeRequest.setAverageSpeed(Double.parseDouble(tableAsMap.get("averageSpeed")));
        scenarioContext.getDriver().setRequestBody(createBikeRequest);
    }

    @Given("a create car request")
    public void aCreateCarRequest(DataTable table) {
        prepareCreateVehicleRequest(new CreateCarRequest(), table);
    }

    @Given("a create pullman request")
    public void aCreatePullmanRequest(DataTable table) {
        prepareCreateVehicleRequest(new CreatePullmanRequest(), table);
    }

    @Given("a create scooter request")
    public void aCreateScooterRequest(DataTable table) {
        prepareCreateVehicleRequest(new CreateScooterRequest(), table);
    }

    @Then("the response should contain the new vehicle's id in the location header")
    public void theResponseShouldContainerTheNewVehiclesIdLocationHeader() {
        String locationHeader = scenarioContext.getDriver().getLastResponse()
                .headers().firstValue("Location").orElseThrow();
        String baseUriAndPath = String.format("%s%s",
                scenarioContext.getDriver().getBaseUri(),
                "/vehicles");

        Assertions.assertEquals(0, locationHeader.indexOf(baseUriAndPath));
        String vehicleIdAsString = locationHeader.substring(baseUriAndPath.length() + 1);
        UUID id = Assertions.assertDoesNotThrow(() -> UUID.fromString(vehicleIdAsString));
        scenarioContext.set(UUID.class, id);
    }

    @Then("a {word} record should be present in the database with that id")
    public void aRecordShouldBePresentInTheDatabaseWithThatId(String vehicleType) {
        UUID id = scenarioContext.get(UUID.class);
        VehicleEntity entity = dao.findById(id).orElse(null);
        Assertions.assertNotNull(entity);
        Assertions.assertEquals(VehicleEntity.VehicleType.valueOf(vehicleType.toUpperCase()),
                entity.getType());
    }

    @Given("the existing bikes")
    public void theExistingBikes(DataTable table) {
        table.asMaps(String.class, String.class)
                .stream()
                .map(VehiclesStepsDefinition::mapToBike)
                .forEach(garage::addVehicle);
    }

    @Then("the response should have {int} vehicles")
    public void theResponseShouldHaveVehicles(int numberOfVehicles) {
        JsonNode response = scenarioContext.getDriver().getLastResponseAs(JsonNode.class);
        Assertions.assertEquals(numberOfVehicles, response.get("vehicles").size());
    }

    @Then("the response should contain the following bikes")
    public void theResponseShouldContainTheFollowingBikes(DataTable table) {
        JsonNode response = scenarioContext.getDriver().getLastResponseAs(JsonNode.class);
        table.asMaps(String.class, String.class)
                .forEach(row -> Assertions.assertTrue(responseContainsRow(response, row)));
    }
    @Given("the existing cars")
    public void theExistingCars(DataTable table) {
        table.asMaps(String.class, String.class)
                .stream()
                .map(VehiclesStepsDefinition::mapToCar)
                .forEach(garage::addVehicle);
    }
    @Given("the existing pullmans")
    public void theExistingPullmans(DataTable table) {
        table.asMaps(String.class, String.class)
                .stream()
                .map(VehiclesStepsDefinition::mapToPullman)
                .forEach(garage::addVehicle);
    }
    @Given("the existing scooters")
    public void theExistingScooters(DataTable table) {
        table.asMaps(String.class, String.class)
                .stream()
                .map(VehiclesStepsDefinition::mapToScooter)
                .forEach(garage::addVehicle);
    }

    private static Bike mapToBike(Map<String, String> map) {
        return new Bike(UUID.fromString(map.get("id")),
                map.get("model"),
                Integer.parseInt(map.get("maxPeople")),
                Double.parseDouble(map.get("dailyRentPrice")),
                Double.parseDouble(map.get("averageSpeed")),
                Double.parseDouble(map.get("autonomy")));
    }


    private static Car mapToCar(Map<String, String> map) {
        return new Car(UUID.fromString(map.get("id")),
                map.get("model"),
                Integer.parseInt(map.get("maxPeople")),
                Double.parseDouble(map.get("dailyRentPrice")),
                Double.parseDouble(map.get("averageSpeed")),
                Double.parseDouble(map.get("autonomy")),
                Integer.parseInt(map.get("stopTimeInSeconds")),
                GenericPlate.from(map.get("plate")),
                FuelType.valueOf(map.get("fuelType")),
                Double.parseDouble(map.get("emissions")),
                Double.parseDouble(map.get("fuelConsumption")));
    }

    private static Pullman mapToPullman(Map<String, String> map) {
        return new Pullman(UUID.fromString(map.get("id")),
                map.get("model"),
                Integer.parseInt(map.get("maxPeople")),
                Double.parseDouble(map.get("dailyRentPrice")),
                Double.parseDouble(map.get("averageSpeed")),
                Double.parseDouble(map.get("autonomy")),
                Integer.parseInt(map.get("stopTimeInSeconds")),
                GenericPlate.from(map.get("plate")),
                FuelType.valueOf(map.get("fuelType")),
                Double.parseDouble(map.get("emissions")),
                Double.parseDouble(map.get("fuelConsumption")));
    }

    private static Scooter mapToScooter(Map<String, String> map) {
        return new Scooter(UUID.fromString(map.get("id")),
                map.get("model"),
                Integer.parseInt(map.get("maxPeople")),
                Double.parseDouble(map.get("dailyRentPrice")),
                Double.parseDouble(map.get("averageSpeed")),
                Double.parseDouble(map.get("autonomy")),
                Integer.parseInt(map.get("stopTimeInSeconds")),
                ScooterPlate.from(map.get("plate")),
                FuelType.valueOf(map.get("fuelType")),
                Double.parseDouble(map.get("emissions")),
                Double.parseDouble(map.get("fuelConsumption")));
    }


    private static boolean responseContainsRow(JsonNode response, Map<String, String> row) {
        return StreamSupport.stream(response.get("vehicles").spliterator(), false)
                .anyMatch(vehicle -> vehicleMatchesRow(row, vehicle));
    }

    private static boolean vehicleMatchesRow(Map<String, String> row, JsonNode vehicle) {
        return row.keySet().stream().allMatch(key ->
                row.get(key).equals(vehicle.get(key).asText()));
    }

    private void prepareCreateVehicleRequest(CreateEnginePoweredVehicleRequest request, DataTable table) {
        Map<String, String> tableAsMap = table.asMaps(String.class, String.class).get(0);
        request.setModel(tableAsMap.get("model"));
        request.setMaxPeople(Integer.parseInt(tableAsMap.get("maxPeople")));
        request.setDailyRentPrice(Double.parseDouble(tableAsMap.get("dailyRentPrice")));
        request.setAutonomy(Double.parseDouble(tableAsMap.get("autonomy")));
        request.setAverageSpeed(Double.parseDouble(tableAsMap.get("averageSpeed")));
        request.setStopTimeInSeconds(Integer.parseInt(tableAsMap.get("stopTimeInSeconds")));
        request.setPlate(tableAsMap.get("plate"));
        request.setFuelType(FuelType.valueOf(tableAsMap.get("fuelType")));
        request.setEmissions(Double.parseDouble(tableAsMap.get("emissions")));
        request.setFuelConsumption(Double.parseDouble(tableAsMap.get("fuelConsumption")));
        scenarioContext.getDriver().setRequestBody(request);
    }
}
