package com.tuimm.learningpath.api.vehicles;

import com.tuimm.learningpath.application.vehicles.queries.GetVehiclesResponse;
import com.tuimm.learningpath.common.mediator.Mediator;
import com.tuimm.learningpath.contracts.vehicles.*;
import com.tuimm.learningpath.application.vehicles.queries.GetAllVehiclesRequest;
import com.tuimm.learningpath.domain.vehicles.Vehicle;
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

@Controller
@RequestMapping(value = "/vehicles")
public class VehicleController {
    private final VehiclesMapper vehiclesMapper;
    private final String vehicleCreatedLocationHeaderEndPath;
    private final Mediator mediator;

    public VehicleController(@NonNull VehiclesMapper vehiclesMapper,
                             @Value("${vehicleController.vehicleCreatedLocationHeaderEndPath}")
                             @NonNull String vehicleCreatedLocationHeaderEndPath,
                             @NonNull Mediator mediator) {
        this.vehiclesMapper = vehiclesMapper;
        this.vehicleCreatedLocationHeaderEndPath = vehicleCreatedLocationHeaderEndPath;
        this.mediator = mediator;
    }

    @GetMapping
    public ResponseEntity<GetVehiclesResponseDto> getVehicles() {
        GetVehiclesResponse getVehiclesResponse = mediator.send(GetAllVehiclesRequest.create());
        GetVehiclesResponseDto responseDto = vehiclesMapper.mapGetVehiclesResponse(getVehiclesResponse);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    @PostMapping("/bikes")
    public ResponseEntity<Object> createBike(@Valid @RequestBody CreateBikeRequestDto request) {
        Vehicle newVehicle = mediator.send(vehiclesMapper.mapCreateBikeRequest(request));
        return vehicleCreated(newVehicle);
    }
    @PostMapping("/cars")
    public ResponseEntity<Object> createBikeCar(@Valid @RequestBody CreateCarRequestDto request) {
        Vehicle newVehicle = mediator.send(vehiclesMapper.mapCreateCarRequest(request));
        return vehicleCreated(newVehicle);
    }
    @PostMapping("/pullmans")
    public ResponseEntity<Object> createPullman(@Valid @RequestBody CreatePullmanRequestDto request) {
        Vehicle newVehicle = mediator.send(vehiclesMapper.mapCreatePullmanRequest(request));
        return vehicleCreated(newVehicle);
    }
    @PostMapping("/scooters")
    public ResponseEntity<Object> createBikeCar(@Valid @RequestBody CreateScooterRequestDto request) {
        Vehicle newVehicle = mediator.send(vehiclesMapper.mapCreateScooterRequest(request));
        return vehicleCreated(newVehicle);
    }

    private ResponseEntity<Object> vehicleCreated(Vehicle vehicle) {
        UriComponents uriComponentsBuilder = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(vehicleCreatedLocationHeaderEndPath)
                .buildAndExpand(vehicle.getId())
                .normalize();
        return ResponseEntity.created(uriComponentsBuilder.toUri()).build();
    }
}
