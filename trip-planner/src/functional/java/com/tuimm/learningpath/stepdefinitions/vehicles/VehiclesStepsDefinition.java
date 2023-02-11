package com.tuimm.learningpath.stepdefinitions.vehicles;

import com.fasterxml.jackson.databind.JsonNode;
import com.tuimm.learningpath.contracts.vehicles.CreateBikeRequest;
import com.tuimm.learningpath.domain.vehicles.Bike;
import com.tuimm.learningpath.domain.vehicles.Garage;
import com.tuimm.learningpath.stepdefinitions.Definition;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.UUID;

public class VehiclesStepsDefinition extends Definition {
    @Autowired
    private Garage garage;
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

    @Then("the response should contain the new vehicle's id in the location header")
    public void theResponseShouldContainerTheNewVehiclesIdLocationHeader() {
        String locationHeader = scenarioContext.getDriver().getLastResponse()
                .headers().firstValue("Location").orElseThrow();
        String baseUriAndPath = String.format("%s%s",
                scenarioContext.getDriver().getBaseUri(),
                "/vehicles");

        Assertions.assertEquals(0, locationHeader.indexOf(baseUriAndPath));
        String vehicleIdAsString = locationHeader.substring(baseUriAndPath.length() + 1);
        Assertions.assertDoesNotThrow(() -> UUID.fromString(vehicleIdAsString));
    }

    @Given("the existing bikes")
    public void theExistingBikes(DataTable table) {
        table.asMaps(String.class, String.class)
                .stream()
                .map(VehiclesStepsDefinition::mapToBike)
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

    @And("the response should have {int} vehicles")
    public void theResponseShouldHaveVehicles(int numberOfVehicles) {
        JsonNode response = scenarioContext.getDriver().getLastResponseAs(JsonNode.class);
        Assertions.assertEquals(numberOfVehicles, response.get("vehicles").size());
    }
}
