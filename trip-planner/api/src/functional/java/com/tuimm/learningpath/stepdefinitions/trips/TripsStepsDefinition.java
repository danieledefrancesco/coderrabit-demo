package com.tuimm.learningpath.stepdefinitions.trips;

import com.fasterxml.jackson.databind.JsonNode;
import com.tuimm.learningpath.TodayDateProvider;
import com.tuimm.learningpath.drivers.dal.DriversDao;
import com.tuimm.learningpath.stepdefinitions.AssertionUtils;
import com.tuimm.learningpath.stepdefinitions.Definition;
import com.tuimm.learningpath.trips.dal.StagePlanEntity;
import com.tuimm.learningpath.trips.dal.TripEntity;
import com.tuimm.learningpath.trips.dal.TripsDao;
import com.tuimm.learningpath.trips.dtos.CreateStageRequestDto;
import com.tuimm.learningpath.trips.dtos.CreateTripRequestDto;
import com.tuimm.learningpath.trips.dtos.PreferredPlanPolicy;
import com.tuimm.learningpath.vehicles.dal.VehiclesDao;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class TripsStepsDefinition extends Definition {
    @Autowired
    private TodayDateProvider todayDateProvider;
    @Autowired
    private TripsDao tripsDao;
    @Autowired
    private DriversDao driversDao;
    @Autowired
    private VehiclesDao vehiclesDao;

    @Given("the need to plan a trip for {int} people starting the {word} at {word} and consisting of the following stages")
    public void theNeedToPlanATripForPeopleStartingTheAtAndConsistingOfTheFollowingStages(int numberOfPeople, String startDateString, String startTimeString, DataTable dataTable) {
        LocalDate startDate = LocalDate.parse(startDateString);
        LocalTime startTime = LocalTime.parse(startTimeString);
        LocalDateTime startDateTime = startDate.atTime(startTime);
        Collection<CreateStageRequestDto> stages = dataTable.asMaps()
                .stream()
                .map(TripsStepsDefinition::mapToCreateStageRequestDto)
                .toList();
        CreateTripRequestDto createTripRequestDto = new CreateTripRequestDto();
        createTripRequestDto.setStart(startDateTime);
        createTripRequestDto.setNumberOfPeople(numberOfPeople);
        createTripRequestDto.setStages(stages);
        scenarioContext.getDriver().setRequestBody(createTripRequestDto);
    }


    @Given("the existing trip {word} with the following stages")
    public void theExistingTripWithTheFollowingStages(String tripIdAsString, DataTable table) {
        UUID tripId = UUID.fromString(tripIdAsString);
        TripEntity tripEntity = new TripEntity();
        tripEntity.setId(tripId);
        tripEntity.setStages(table.asMaps().stream()
                .map(map -> mapToStagePlanEntity(map, tripId))
                .collect(Collectors.toSet()));
        tripsDao.save(tripEntity);
    }

    @Then("the response should contain the new trip's id in the location header")
    public void theResponseShouldContainerTheNewTripsIdLocationHeader() {
        String locationHeader = scenarioContext.getDriver().getLastResponse()
                .headers().firstValue("Location").orElseThrow();
        String baseUriAndPath = String.format("%s%s",
                scenarioContext.getDriver().getBaseUri(),
                "/trips");

        Assertions.assertEquals(0, locationHeader.indexOf(baseUriAndPath));
        String vehicleIdAsString = locationHeader.substring(baseUriAndPath.length() + 1);
        UUID id = Assertions.assertDoesNotThrow(() -> UUID.fromString(vehicleIdAsString));
        scenarioContext.set(UUID.class, id);
    }

    @Then("a trip should be stored in the database with the following stages")
    public void aTripShouldBeStoredInTheDatabaseWithTheFollowingStages(DataTable table) {
        UUID tripId = scenarioContext.get(UUID.class);
        TripEntity entity = tripsDao.findById(tripId).orElseThrow();
        table.asMaps().forEach(map -> entity.getStages().stream()
                .filter(stage -> stage.getStartDateTime().equals(LocalDateTime.parse(map.get("startDateTime"))))
                .findFirst()
                .ifPresentOrElse(stagePlanEntity -> assertStagePlanEntityMatchesMap(stagePlanEntity, map),
                        () -> {
                            throw new UnsupportedOperationException("No such stage");
                        }));
    }

    @Then("the response should consist of the following stages")
    public void theResponseShouldConsistsOfTheFollowingStages(DataTable table) {
        JsonNode stages = scenarioContext.getDriver().getLastResponseAs(JsonNode.class).get("plan").get("stages");
        List<Map<String, String>> maps = table.asMaps();
        Assertions.assertEquals(maps.size(), stages.size());
        stages.forEach(stage -> AssertionUtils.assertMapMatchesJson(maps.stream()
                .filter(map -> stage.get("start_date_time").asText().equals(map.get("start_date_time")))
                .findFirst()
                .get(), stage));
    }

    private void assertStagePlanEntityMatchesMap(StagePlanEntity stagePlanEntity, Map<String, String> map) {
        Assertions.assertEquals(map.get("destinationWeatherCondition"), stagePlanEntity.getDestinationWeatherCondition());
        Assertions.assertEquals(Double.parseDouble(map.get("distanceInKilometers")), stagePlanEntity.getDistanceInKilometers(), 0);
        Assertions.assertEquals(UUID.fromString(map.get("driverId")), stagePlanEntity.getDriver().getId());
        Assertions.assertEquals(map.get("drivingProfile"), stagePlanEntity.getDrivingProfile());
        Assertions.assertEquals(Double.parseDouble(map.get("fromLatitude")), stagePlanEntity.getFromLatitude(), 0);
        Assertions.assertEquals(Double.parseDouble(map.get("fromLongitude")), stagePlanEntity.getFromLongitude(), 0);
        Assertions.assertEquals(map.get("fromName"), stagePlanEntity.getFromName());
        Assertions.assertEquals(Double.parseDouble(map.get("toLatitude")), stagePlanEntity.getToLatitude(), 0);
        Assertions.assertEquals(Double.parseDouble(map.get("toLongitude")), stagePlanEntity.getToLongitude(), 0);
        Assertions.assertEquals(map.get("toName"), stagePlanEntity.getToName());
        Assertions.assertEquals(Integer.parseInt(map.get("numberOfPeople")), stagePlanEntity.getNumberOfPeople());
        Assertions.assertEquals(UUID.fromString(map.get("vehicleId")), stagePlanEntity.getVehicle().getId());
    }

    private static CreateStageRequestDto mapToCreateStageRequestDto(Map<String, String> map) {
        CreateStageRequestDto result = new CreateStageRequestDto();
        result.setFrom(map.get("from"));
        result.setTo(map.get("to"));
        result.setPreferredPlanPolicy(PreferredPlanPolicy.valueOf(map.get("preferredPlanPolicy")));
        return result;
    }

    private StagePlanEntity mapToStagePlanEntity(Map<String, String> map, UUID tripId) {
        StagePlanEntity result = new StagePlanEntity();
        result.setTripId(tripId);
        result.setStartDateTime(LocalDateTime.parse(map.get("startDateTime")));
        result.setDestinationWeatherCondition(map.get("destinationWeatherCondition"));
        result.setDistanceInKilometers(Double.parseDouble(map.get("distanceInKilometers")));
        result.setDriver(driversDao.findById(UUID.fromString(map.get("driverId"))).orElseThrow());
        result.setDrivingProfile(map.get("drivingProfile"));
        result.setFromLatitude(Double.parseDouble(map.get("fromLatitude")));
        result.setFromLongitude(Double.parseDouble(map.get("fromLongitude")));
        result.setFromName(map.get("fromName"));
        result.setToLatitude(Double.parseDouble(map.get("toLatitude")));
        result.setToLongitude(Double.parseDouble(map.get("toLongitude")));
        result.setToName(map.get("toName"));
        result.setNumberOfPeople(Integer.parseInt(map.get("numberOfPeople")));
        result.setVehicle(vehiclesDao.findById(UUID.fromString(map.get("vehicleId"))).orElseThrow());
        return result;
    }
}
