package com.tuimm.leaarningpath;

import com.googlecode.cqengine.ConcurrentIndexedCollection;
import com.googlecode.cqengine.IndexedCollection;
import com.tuimm.leaarningpath.cli.*;
import com.tuimm.leaarningpath.common.RandomIdGenerator;
import com.tuimm.leaarningpath.common.RandomIdGeneratorImpl;
import com.tuimm.leaarningpath.domain.places.PlacesService;
import com.tuimm.leaarningpath.domain.routes.RoutesService;
import com.tuimm.leaarningpath.domain.trips.*;
import com.tuimm.leaarningpath.domain.vehicles.*;
import com.tuimm.leaarningpath.domain.weatherconditions.WeatherConditionsService;
import com.tuimm.leaarningpath.infrastructure.places.PlacesServiceImpl;
import com.tuimm.leaarningpath.infrastructure.routes.RoutesServiceImpl;
import com.tuimm.leaarningpath.infrastructure.weatherconditions.WeatherConditionServiceImpl;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.InputStream;
import java.io.PrintStream;
import java.net.http.HttpClient;
import java.util.*;
import java.util.function.Supplier;

public class AdvanceChallenge {
    private AdvanceChallenge() {

    }
    public static void main(String... args) {
        Locale.setDefault(Locale.US);
        ServiceLocator.getInstance().commandLineInterfaceSupplier.get().run();
    }
    @Getter
    @Setter
    static class ServiceLocator {
        private ServiceLocator() {

        }
        private static final ServiceLocator instance = new ServiceLocator();
        public static ServiceLocator getInstance() {
            return instance;
        }
        private Supplier<RandomIdGenerator> randomIdGeneratorSupplier = SingletonSupplier.create(RandomIdGeneratorImpl::new);
        private Supplier<IndexedCollection<Vehicle>> vehicleIndexedCollectionSupplier = SingletonSupplier.create(ConcurrentIndexedCollection::new);
        private Supplier<VehicleFactory> vehicleFactorySupplier = SingletonSupplier.create(() -> new VehicleFactoryImpl(randomIdGeneratorSupplier.get()));
        private Supplier<Garage> garageSupplier = SingletonSupplier.create(() -> new CQEngineBasedGarage(vehicleIndexedCollectionSupplier.get()));
        private Supplier<VehiclesService> vehiclesServiceSupplier = SingletonSupplier.create(() -> new VehiclesServiceImpl(garageSupplier.get(), vehicleFactorySupplier.get()));
        private Supplier<HttpClient> httpClientSupplier = SingletonSupplier.create(() -> HttpClient.newBuilder().build());
        private Supplier<String> weatherServiceBaseUriSupplier = SingletonSupplier.create(() -> Optional.ofNullable(System.getenv("WEATHER_SERVICE_BASE_URI"))
                .orElse("http://localhost:8080/weather-service"));
        private Supplier<String> destinationsServiceBaseUriSupplier = SingletonSupplier.create(() -> Optional.ofNullable(System.getenv("DIRECTIONS_SERVICE_BASE_URI"))
                .orElse("http://localhost:8080/directions-service"));
        private Supplier<String> geocodingServiceBaseUriSupplier = SingletonSupplier.create(() -> Optional.ofNullable(System.getenv("GEOCODING_SERVICE_BASE_URI"))
                .orElse("http://localhost:8080/geocode-service"));
        private Supplier<String> apiKeySupplier = SingletonSupplier.create(() -> Optional.ofNullable(System.getenv("API_KEY"))
                .orElse("12345678"));
        private Supplier<WeatherConditionsService> weatherConditionsServiceSupplier = SingletonSupplier.create(() -> new WeatherConditionServiceImpl(httpClientSupplier.get(), weatherServiceBaseUriSupplier.get()));
        private Supplier<PlacesService> placesServiceSupplier = SingletonSupplier.create(() -> new PlacesServiceImpl(httpClientSupplier.get(), geocodingServiceBaseUriSupplier.get(), apiKeySupplier.get()));
        private Supplier<RoutesService> routesServiceSupplier = SingletonSupplier.create(() -> new RoutesServiceImpl(httpClientSupplier.get(), destinationsServiceBaseUriSupplier.get(), apiKeySupplier.get()));
        private Supplier<TripPlansService> tripPlansServiceSupplier = SingletonSupplier.create(() -> new TripPlansServiceImpl(
                garageSupplier.get(),
                weatherConditionsServiceSupplier.get(),
                placesServiceSupplier.get(),
                routesServiceSupplier.get(),
                StagePlan::builder));
        private Supplier<List<CommandFactory>> commandFactoriesSupplier = () -> {
            List<CommandFactory> commandFactories = new ArrayList<>();
            Map<String, Comparator<StagePlan>> comparators = new HashMap<>();
            comparators.put("CHEAPEST", PlanByCheaper.getInstance());
            comparators.put("FASTEST", PlanByFaster.getInstance());
            comparators.put("LEAST_POLLUTING", PlanByLessPolluting.getInstance());
            commandFactories.add(new SetFuelCostCommandFactory(vehiclesServiceSupplier.get()));
            commandFactories.add(new CreateBikeCommandFactory(vehiclesServiceSupplier.get()));
            commandFactories.add(new CreateCarCommandFactory(vehiclesServiceSupplier.get()));
            commandFactories.add(new CreatePullmanCommandFactory(vehiclesServiceSupplier.get()));
            commandFactories.add(new CreateScooterCommandFactory(vehiclesServiceSupplier.get()));
            commandFactories.add(new ShowGarageCommandFactory(vehiclesServiceSupplier.get()));
            commandFactories.add(new PlanTripCommandFactory(tripPlansServiceSupplier.get(), comparators));
            commandFactories.add(new EndProgramCommandFactory());
            return commandFactories;
        };
        private Supplier<PrintStream> printStreamSupplier = () -> System.out;
        private Supplier<InputStream> inputStreamSupplier = () -> System.in;
        private Supplier<CommandLineInterface> commandLineInterfaceSupplier = SingletonSupplier.create(() -> new CommandLineInterface(inputStreamSupplier.get(),
                printStreamSupplier.get(),
                commandFactoriesSupplier.get()));

        @RequiredArgsConstructor(staticName = "create")
        private static class SingletonSupplier<T> implements Supplier<T> {
            private final Supplier<T> delegate;
            private T value;
            @Override
            public T get() {
                if (value == null) {
                    value = delegate.get();
                }
                return value;
            }
        }
    }
}
