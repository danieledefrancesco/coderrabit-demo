package com.tuimm.learningpath.stepdefinitions.vehicles;

import com.fasterxml.jackson.databind.JsonNode;
import com.tuimm.learningpath.stepdefinitions.Definition;
import com.tuimm.learningpath.vehicles.*;
import com.tuimm.learningpath.vehicles.dal.VehicleEntity;
import com.tuimm.learningpath.vehicles.dal.VehiclesDao;
import com.tuimm.learningpath.vehicles.dtos.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
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

    @Then("a vehicle of type {word} should be present in the database with that id")
    public void aVehicleOfTypeShouldBePresentInTheDatabaseWithThatId(String vehicleType) {
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
                .forEach(row -> assertResponseContainsRow(response, row));
    }

    @Then("the response should contain the following bike")
    public void theResponseShouldContainTheFollowingBike(DataTable table) {
        JsonNode response = scenarioContext.getDriver().getLastResponseAs(JsonNode.class);
        assertVehicleMatchesRow(table.asMaps(String.class, String.class).get(0), response);
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

    @Then("the vehicle with id {word} should no longer exist")
    public void theVehicleWithIdShouldNoLongerExist(String id) {
        VehicleEntity entity = dao.findById(UUID.fromString(id)).orElse(null);
        Assertions.assertNull(entity);
    }

    @Given("the {word} cost is {double}")
    public void theFuelCostIs(String fuelType, double cost) {
        FuelType.valueOf(fuelType).setCost(cost);
    }



    @Given("the need to update the cost to {double}")
    public void theNeedToUpdateTheCostToFuelTypeCost(double cost) {
        UpdateFuelTypeCostRequestDto updateFuelTypeCostRequestDto = new UpdateFuelTypeCostRequestDto();
        updateFuelTypeCostRequestDto.setCost(cost);
        scenarioContext.getDriver().setRequestBody(updateFuelTypeCostRequestDto);
    }

    @Then("the response should contain the following fuel types")
    public void theResponseShouldContainTheFollowingFuelTypes(DataTable table) {
        GetAllFuelTypesResponseDto getAllFuelTypesResponseDto = scenarioContext.getDriver().getLastResponseAs(GetAllFuelTypesResponseDto.class);
        Collection<Map<String, String>> expectedFuelTypes = table.asMaps();
        for (Map<String, String> expectedFuelType : expectedFuelTypes) {
            Assertions.assertTrue(getAllFuelTypesResponseDto.getFuelTypes().stream().anyMatch(actualFuelType ->
                    actualFuelType.getCost() == Double.parseDouble(expectedFuelType.get("cost")) &&
                            actualFuelType.getName().equals(expectedFuelType.get("name"))));
        }
    }

    @Then("the {word} cost should be {double}")
    public void theFuelTypeCostShouldBeFuelTypeCost(String fuelTypeAsString, double cost) {
        Assertions.assertEquals(cost, FuelType.valueOf(fuelTypeAsString).getCost(), 0);
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


    private static void assertResponseContainsRow(JsonNode response, Map<String, String> row) {
        StreamSupport.stream(response.get("vehicles").spliterator(), false)
                .filter(vehicle -> vehicle.get("id").asText().equals(row.get("id")))
                .findFirst()
                .ifPresentOrElse(vehicle -> assertVehicleMatchesRow(row, vehicle),
                        () -> {
                            throw new UnsupportedOperationException("no such vehicle");
                        });
    }

    private static void assertVehicleMatchesRow(Map<String, String> row, JsonNode vehicle) {
        row.keySet().forEach(key -> Assertions.assertEquals(row.get(key), vehicle.get(key).asText()));
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
