package com.tuimm.learningpath.stepdefinitions.vehicles;

import com.fasterxml.jackson.databind.JsonNode;
import com.tuimm.learningpath.contracts.vehicles.*;
import com.tuimm.learningpath.domain.vehicles.*;
import com.tuimm.learningpath.infrastructure.vehicles.dal.VehiclesDao;
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
    private VehiclesDao dao;

    @Given("a create bike request")
    public void aCreateBikeRequest(DataTable table) {
        Map<String, String> tableAsMap = table.asMaps(String.class, String.class).get(0);
        CreateBikeRequestDto createBikeRequestDto = new CreateBikeRequestDto();
        createBikeRequestDto.setModel(tableAsMap.get("model"));
        createBikeRequestDto.setMaxPeople(Integer.parseInt(tableAsMap.get("maxPeople")));
        createBikeRequestDto.setDailyRentPrice(Double.parseDouble(tableAsMap.get("dailyRentPrice")));
        createBikeRequestDto.setAutonomy(Double.parseDouble(tableAsMap.get("autonomy")));
        createBikeRequestDto.setAverageSpeed(Double.parseDouble(tableAsMap.get("averageSpeed")));
        scenarioContext.getDriver().setRequestBody(createBikeRequestDto);
    }

    @Given("a create car request")
    public void aCreateCarRequest(DataTable table) {
        prepareCreateVehicleRequest(new CreateCarRequestDto(), table);
    }

    @Given("a create pullman request")
    public void aCreatePullmanRequest(DataTable table) {
        prepareCreateVehicleRequest(new CreatePullmanRequestDto(), table);
    }

    @Given("a create scooter request")
    public void aCreateScooterRequest(DataTable table) {
        prepareCreateVehicleRequest(new CreateScooterRequestDto(), table);
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
        return Bike.builder()
                .id(UUID.fromString(map.get("id")))
                .model(map.get("model"))
                .maxPeople(Integer.parseInt(map.get("maxPeople")))
                .dailyRentPrice(Double.parseDouble(map.get("dailyRentPrice")))
                .averageSpeed(Double.parseDouble(map.get("averageSpeed")))
                .autonomy(Double.parseDouble(map.get("autonomy")))
                .build();
    }


    private static Car mapToCar(Map<String, String> map) {
        return Car.builder()
                .stopTimeInSeconds(Integer.parseInt(map.get("stopTimeInSeconds")))
                .plate(GenericPlate.from(map.get("plate")))
                .fuelType(FuelType.valueOf(map.get("fuelType")))
                .emissions(Double.parseDouble(map.get("emissions")))
                .fuelConsumption(Double.parseDouble(map.get("fuelConsumption")))
                .id(UUID.fromString(map.get("id")))
                .model(map.get("model"))
                .maxPeople(Integer.parseInt(map.get("maxPeople")))
                .dailyRentPrice(Double.parseDouble(map.get("dailyRentPrice")))
                .averageSpeed(Double.parseDouble(map.get("averageSpeed")))
                .autonomy(Double.parseDouble(map.get("autonomy")))
                .build();
    }

    private static Pullman mapToPullman(Map<String, String> map) {
        return Pullman.builder()
                .stopTimeInSeconds(Integer.parseInt(map.get("stopTimeInSeconds")))
                .plate(GenericPlate.from(map.get("plate")))
                .fuelType(FuelType.valueOf(map.get("fuelType")))
                .emissions(Double.parseDouble(map.get("emissions")))
                .fuelConsumption(Double.parseDouble(map.get("fuelConsumption")))
                .id(UUID.fromString(map.get("id")))
                .model(map.get("model"))
                .maxPeople(Integer.parseInt(map.get("maxPeople")))
                .dailyRentPrice(Double.parseDouble(map.get("dailyRentPrice")))
                .averageSpeed(Double.parseDouble(map.get("averageSpeed")))
                .autonomy(Double.parseDouble(map.get("autonomy")))
                .build();
    }

    private static Scooter mapToScooter(Map<String, String> map) {
        return Scooter.builder()
                .stopTimeInSeconds(Integer.parseInt(map.get("stopTimeInSeconds")))
                .plate(ScooterPlate.from(map.get("plate")))
                .fuelType(FuelType.valueOf(map.get("fuelType")))
                .emissions(Double.parseDouble(map.get("emissions")))
                .fuelConsumption(Double.parseDouble(map.get("fuelConsumption")))
                .id(UUID.fromString(map.get("id")))
                .model(map.get("model"))
                .maxPeople(Integer.parseInt(map.get("maxPeople")))
                .dailyRentPrice(Double.parseDouble(map.get("dailyRentPrice")))
                .averageSpeed(Double.parseDouble(map.get("averageSpeed")))
                .autonomy(Double.parseDouble(map.get("autonomy")))
                .build();
    }


    private static boolean responseContainsRow(JsonNode response, Map<String, String> row) {
        return StreamSupport.stream(response.get("vehicles").spliterator(), false)
                .anyMatch(vehicle -> vehicleMatchesRow(row, vehicle));
    }

    private static boolean vehicleMatchesRow(Map<String, String> row, JsonNode vehicle) {
        return row.keySet().stream().allMatch(key ->
                row.get(key).equals(vehicle.get(key).asText()));
    }

    private void prepareCreateVehicleRequest(CreateEnginePoweredVehicleRequestDto request, DataTable table) {
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
