package com.tuimm.learningpath.api.vehicles;

import com.tuimm.learningpath.contracts.vehicles.*;
import com.tuimm.learningpath.domain.vehicles.Vehicle;
import com.tuimm.learningpath.domain.vehicles.VehiclesService;
import jakarta.validation.Valid;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import java.util.Collection;

@Controller
@RequestMapping(value = "/vehicles")
public class VehicleController {
    private final VehiclesService vehiclesService;
    private final VehiclesMapper vehiclesMapper;
    private final String vehicleCreatedLocationHeaderEndPath;

    public VehicleController(@NonNull VehiclesService vehiclesService,
                             @NonNull VehiclesMapper vehiclesMapper,
                             @Value("${vehicleController.vehicleCreatedLocationHeaderEndPath}")
                             @NonNull String vehicleCreatedLocationHeaderEndPath) {
        this.vehiclesService = vehiclesService;
        this.vehiclesMapper = vehiclesMapper;
        this.vehicleCreatedLocationHeaderEndPath = vehicleCreatedLocationHeaderEndPath;
    }

    @GetMapping
    public ResponseEntity<GetVehiclesResponse> getVehicles() {
        Collection<VehicleResponse> vehicleResponses = vehiclesService.getAllVehicles()
                .stream()
                .map(vehiclesMapper::mapVehicle)
                .toList();
        GetVehiclesResponse response = new GetVehiclesResponse();
        response.setVehicles(vehicleResponses);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/bikes")
    public ResponseEntity<Object> createBike(@Valid @RequestBody CreateBikeRequest request) {
        Vehicle vehicle = vehiclesService.addBike(request.getModel(),
                request.getMaxPeople(),
                request.getDailyRentPrice(),
                request.getAverageSpeed(),
                request.getAutonomy());
        UriComponents uriComponentsBuilder = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(vehicleCreatedLocationHeaderEndPath)
                .buildAndExpand(vehicle.getId())
                .normalize();
        return ResponseEntity.created(uriComponentsBuilder.toUri()).build();
    }
    @PostMapping("/cars")
    public ResponseEntity<Object> createBikeCar(@Valid @RequestBody CreateCarRequest request) {
        Vehicle vehicle = vehiclesService.addCar(request.getModel(),
                request.getMaxPeople(),
                request.getDailyRentPrice(),
                request.getAverageSpeed(),
                request.getAutonomy(),
                request.getStopTimeInSeconds(),
                request.getPlate(),
                request.getFuelType(),
                request.getEmissions(),
                request.getFuelConsumption());
        UriComponents uriComponentsBuilder = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(vehicleCreatedLocationHeaderEndPath)
                .buildAndExpand(vehicle.getId())
                .normalize();
        return ResponseEntity.created(uriComponentsBuilder.toUri()).build();
    }
    @PostMapping("/pullmans")
    public ResponseEntity<Object> createPullman(@Valid @RequestBody CreatePullmanRequest request) {
        Vehicle vehicle = vehiclesService.addPullman(request.getModel(),
                request.getMaxPeople(),
                request.getDailyRentPrice(),
                request.getAverageSpeed(),
                request.getAutonomy(),
                request.getStopTimeInSeconds(),
                request.getPlate(),
                request.getFuelType(),
                request.getEmissions(),
                request.getFuelConsumption());
        UriComponents uriComponentsBuilder = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(vehicleCreatedLocationHeaderEndPath)
                .buildAndExpand(vehicle.getId())
                .normalize();
        return ResponseEntity.created(uriComponentsBuilder.toUri()).build();
    }
    @PostMapping("/scooters")
    public ResponseEntity<Object> createBikeCar(@Valid @RequestBody CreateScooterRequest request) {
        Vehicle vehicle = vehiclesService.addScooter(request.getModel(),
                request.getMaxPeople(),
                request.getDailyRentPrice(),
                request.getAverageSpeed(),
                request.getAutonomy(),
                request.getStopTimeInSeconds(),
                request.getPlate(),
                request.getFuelType(),
                request.getEmissions(),
                request.getFuelConsumption());
        UriComponents uriComponentsBuilder = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(vehicleCreatedLocationHeaderEndPath)
                .buildAndExpand(vehicle.getId())
                .normalize();
        return ResponseEntity.created(uriComponentsBuilder.toUri()).build();
    }
}
