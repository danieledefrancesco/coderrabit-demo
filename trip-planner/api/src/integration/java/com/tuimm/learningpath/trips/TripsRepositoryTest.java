package com.tuimm.learningpath.trips;

import com.tuimm.learningpath.IntegrationTest;
import com.tuimm.learningpath.drivers.dal.DriverEntity;
import com.tuimm.learningpath.drivers.dal.DriversDao;
import com.tuimm.learningpath.drivers.dal.DrivingLicenseEntity;
import com.tuimm.learningpath.trips.dal.StagePlanEntity;
import com.tuimm.learningpath.trips.dal.TripEntity;
import com.tuimm.learningpath.trips.dal.TripsDao;
import com.tuimm.learningpath.vehicles.dal.VehicleEntity;
import com.tuimm.learningpath.vehicles.dal.VehiclesDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

class TripsRepositoryTest extends IntegrationTest {
    @Autowired
    private TripsDao tripsDao;
    @Autowired
    private VehiclesDao vehiclesDao;
    @Autowired
    private DriversDao driversDao;

    @BeforeEach
    @AfterEach
    void setUp() {
        tripsDao.deleteAll();
        vehiclesDao.deleteAll();
        driversDao.deleteAll();
    }

    @Test
    void add_shouldCreateTheTripInTheDatabase() {
        UUID tripID = UUID.randomUUID();
        TripEntity tripEntity = new TripEntity();
        tripEntity.setId(tripID);
        Set<StagePlanEntity> stagePlanEntities = new HashSet<>();

        StagePlanEntity stagePlan1 = createAndStoreFirstStagePlan(tripID);
        StagePlanEntity stagePlan2 = createAndStoreSecondStagePlan(tripID);
        stagePlanEntities.add(stagePlan1);
        stagePlanEntities.add(stagePlan2);

        tripEntity.setStages(stagePlanEntities);
        tripsDao.save(tripEntity);
        Assertions.assertEquals(1, tripsDao.count());
        TripEntity retrievedTrip = tripsDao.findAll().iterator().next();
        Assertions.assertEquals(tripID, retrievedTrip.getId());
        Assertions.assertEquals(2, retrievedTrip.getStages().size());
        StagePlanEntity retrievedPlan1 = retrievedTrip.getStages().stream().toList().get(0);
        StagePlanEntity retrievedPlan2 = retrievedTrip.getStages().stream().toList().get(1);
        Assertions.assertNotNull(retrievedPlan1.getDriver());
        Assertions.assertNotNull(retrievedPlan1.getVehicle());
        Assertions.assertNotNull(retrievedPlan2.getDriver());
        Assertions.assertNotNull(retrievedPlan2.getVehicle());
        retrievedTrip.getStages().remove(retrievedPlan1);
        tripsDao.save(retrievedTrip);
        TripEntity retrievedTrip2 = tripsDao.findAll().iterator().next();
        Assertions.assertEquals(1, retrievedTrip.getStages().size());

    }

    private StagePlanEntity createAndStoreFirstStagePlan(UUID tripID) {
        DriverEntity driverEntity = createAndStoreFirstDriver();
        VehicleEntity vehicle = createAndStoreFirstVehicle();
        StagePlanEntity stagePlan = new StagePlanEntity();
        stagePlan.setTripId(tripID);
        stagePlan.setFromName("ROME");
        stagePlan.setFromLatitude(1);
        stagePlan.setFromLongitude(2);
        stagePlan.setToName("MILAN");
        stagePlan.setToLatitude(3);
        stagePlan.setToLongitude(4);
        stagePlan.setDrivingProfile("DRIVING_CAR");
        stagePlan.setDestinationWeatherCondition("SUNNY");
        stagePlan.setNumberOfPeople(5);
        stagePlan.setDriver(driverEntity);
        stagePlan.setVehicle(vehicle);
        stagePlan.setDistanceInKilometers(101);
        stagePlan.setStartDateTime(LocalDateTime.of(2023,1,1,14,0,0));
        return stagePlan;
    }

    private StagePlanEntity createAndStoreSecondStagePlan(UUID tripID) {
        DriverEntity driverEntity = createAndStoreSecondDriver();
        VehicleEntity vehicle = createAndStoreSecondVehicle();
        StagePlanEntity stagePlan = new StagePlanEntity();
        stagePlan.setTripId(tripID);
        stagePlan.setFromName("Milan");
        stagePlan.setFromLatitude(3);
        stagePlan.setFromLongitude(4);
        stagePlan.setToName("Zurich");
        stagePlan.setToLatitude(5);
        stagePlan.setToLongitude(6);
        stagePlan.setDrivingProfile("DRIVING_PULLMAN");
        stagePlan.setDestinationWeatherCondition("WINDY");
        stagePlan.setNumberOfPeople(5);
        stagePlan.setDriver(driverEntity);
        stagePlan.setVehicle(vehicle);
        stagePlan.setDistanceInKilometers(100);
        stagePlan.setStartDateTime(LocalDateTime.of(2023,1,1,9,0,0));
        return stagePlan;
    }

    private VehicleEntity createAndStoreFirstVehicle() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setFuelType("PETROL");
        vehicle.setAutonomy(1);
        vehicle.setModel("model1");
        vehicle.setId(UUID.randomUUID());
        vehicle.setMaxPeople(2);
        vehicle.setType(VehicleEntity.VehicleType.CAR);
        vehicle.setEmissions(3);
        vehicle.setPlate("AA000BB");
        vehicle.setAverageSpeed(4);
        vehicle.setDailyRentPrice(5);
        vehicle.setStopTimeInSeconds(6);
        vehicle.setFuelConsumption(7);
        vehiclesDao.save(vehicle);
        return vehicle;
    }

    private VehicleEntity createAndStoreSecondVehicle() {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setFuelType("DIESEL");
        vehicle.setAutonomy(8);
        vehicle.setModel("model2");
        vehicle.setId(UUID.randomUUID());
        vehicle.setMaxPeople(9);
        vehicle.setType(VehicleEntity.VehicleType.PULLMAN);
        vehicle.setEmissions(10);
        vehicle.setPlate("CC000DD");
        vehicle.setAverageSpeed(11);
        vehicle.setDailyRentPrice(12);
        vehicle.setStopTimeInSeconds(13);
        vehicle.setFuelConsumption(14);
        vehiclesDao.save(vehicle);
        return vehicle;
    }

    private DriverEntity createAndStoreFirstDriver() {
        DriverEntity driverEntity = new DriverEntity();
        DrivingLicenseEntity drivingLicense = new DrivingLicenseEntity();
        driverEntity.setId(UUID.randomUUID());
        driverEntity.setDrivingLicense(drivingLicense);
        driverEntity.setDateOfBirth(LocalDate.of(2000,1,1));
        driverEntity.setCitizenship("Italian");
        driverEntity.setFirstName("Mario");
        driverEntity.setLastName("Rossi");
        drivingLicense.setExpiryDate(LocalDate.of(2025,12,12));
        drivingLicense.setCode("ABC123");
        driversDao.save(driverEntity);
        return driverEntity;
    }

    private DriverEntity createAndStoreSecondDriver() {
        DriverEntity driverEntity = new DriverEntity();
        DrivingLicenseEntity drivingLicense = new DrivingLicenseEntity();
        driverEntity.setId(UUID.randomUUID());
        driverEntity.setDrivingLicense(drivingLicense);
        driverEntity.setDateOfBirth(LocalDate.of(2001,1,1));
        driverEntity.setCitizenship("American");
        driverEntity.setFirstName("John");
        driverEntity.setLastName("Smith");
        drivingLicense.setExpiryDate(LocalDate.of(2026,12,12));
        drivingLicense.setCode("DEF456");
        driversDao.save(driverEntity);
        return driverEntity;
    }
}
