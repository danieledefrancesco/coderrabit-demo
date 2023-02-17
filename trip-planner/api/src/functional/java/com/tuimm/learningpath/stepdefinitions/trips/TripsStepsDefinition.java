package com.tuimm.learningpath.stepdefinitions.trips;

import com.tuimm.learningpath.TodayDateProvider;
import com.tuimm.learningpath.stepdefinitions.Definition;
import com.tuimm.learningpath.trips.dal.StagePlanEntity;
import com.tuimm.learningpath.trips.dal.TripEntity;
import com.tuimm.learningpath.trips.dal.TripsDao;
import com.tuimm.learningpath.trips.dtos.CreateStageRequestDto;
import com.tuimm.learningpath.trips.dtos.CreateTripRequestDto;
import com.tuimm.learningpath.trips.dtos.PreferredPlanPolicy;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

public class TripsStepsDefinition extends Definition {
    @Autowired
    private TodayDateProvider todayDateProvider;
    @Autowired
    private TripsDao tripsDao;

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
                        () -> {throw new UnsupportedOperationException("No such stage"); }));
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
}
