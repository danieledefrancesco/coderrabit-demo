package com.tuimm.leaarningpath;

import com.googlecode.cqengine.ConcurrentIndexedCollection;
import com.tuimm.leaarningpath.cli.*;
import com.tuimm.leaarningpath.common.RandomIdGenerator;
import com.tuimm.leaarningpath.common.RandomIdGeneratorImpl;
import com.tuimm.leaarningpath.domain.places.PlacesService;
import com.tuimm.leaarningpath.domain.routes.RoutesService;
import com.tuimm.leaarningpath.domain.trips.*;
import com.tuimm.leaarningpath.domain.vehicles.CQEngineBasedGarage;
import com.tuimm.leaarningpath.domain.vehicles.VehicleFactoryImpl;
import com.tuimm.leaarningpath.domain.vehicles.VehiclesService;
import com.tuimm.leaarningpath.domain.vehicles.VehiclesServiceImpl;
import com.tuimm.leaarningpath.domain.weatherconditions.WeatherConditionsService;
import com.tuimm.leaarningpath.infrastructure.places.PlacesServiceImpl;
import com.tuimm.leaarningpath.infrastructure.routes.RoutesServiceImpl;
import com.tuimm.leaarningpath.infrastructure.weatherconditions.WeatherConditionServiceImpl;

import java.net.http.HttpClient;
import java.util.*;

public class AdvanceChallenge {
  public static void main(String[] args) {
    createCommandLineInterface().run();
  }

  private static CommandLineInterface createCommandLineInterface() {
    RandomIdGenerator randomIdGenerator = new RandomIdGeneratorImpl();
    CQEngineBasedGarage garage = new CQEngineBasedGarage(new ConcurrentIndexedCollection<>());
    VehicleFactoryImpl vehicleFactory = new VehicleFactoryImpl(randomIdGenerator);
    VehiclesService vehiclesService = new VehiclesServiceImpl(garage, vehicleFactory);
    HttpClient httpClient = HttpClient.newBuilder().build();
    String weatherServiceBaseUri = Optional.ofNullable(System.getenv("WEATHER_SERVICE_BASE_URI"))
            .orElse("http://localhost:8080/weather-service");
    String destinationsServiceBaseUri = Optional.ofNullable(System.getenv("DIRECTIONS_SERVICE_BASE_URI"))
            .orElse("http://localhost:8080/directions-service");
    String geocodingServiceBaseUri = Optional.ofNullable(System.getenv("GEOCODING_SERVICE_BASE_URI"))
            .orElse("http://localhost:8080/geocode-service");
    String apiKey = Optional.ofNullable(System.getenv("API_KEY"))
            .orElse("12345678");
    WeatherConditionsService weatherConditionsService = new WeatherConditionServiceImpl(httpClient, weatherServiceBaseUri);
    PlacesService placesService = new PlacesServiceImpl(httpClient, geocodingServiceBaseUri, apiKey);
    RoutesService routesService = new RoutesServiceImpl(httpClient, destinationsServiceBaseUri, apiKey);
    TripPlansService tripPlansService = new TripPlansServiceImpl(
            garage,
            weatherConditionsService,
            placesService,
            routesService,
            StagePlan::builder);
    List<CommandFactory> commandFactories = new ArrayList<>();
    Map<String, Comparator<StagePlan>> comparators = new HashMap<>();
    comparators.put("CHEAPEST", PlanByCheaper.getInstance());
    comparators.put("FASTEST", PlanByFaster.getInstance());
    comparators.put("LEAST_POLLUTING", PlanByLessPolluting.getInstance());
    commandFactories.add(new SetFuelCostCommandFactory(vehiclesService));
    commandFactories.add(new CreateBikeCommandFactory(vehiclesService));
    commandFactories.add(new CreateCarCommandFactory(vehiclesService));
    commandFactories.add(new CreatePullmanCommandFactory(vehiclesService));
    commandFactories.add(new CreateScooterCommandFactory(vehiclesService));
    commandFactories.add(new ShowGarageCommandFactory(vehiclesService));
    commandFactories.add(new PlanTripCommandFactory(tripPlansService, comparators));
    commandFactories.add(new EndProgramCommandFactory());
    return new CommandLineInterface(System.in, System.out, commandFactories);
  }
}
