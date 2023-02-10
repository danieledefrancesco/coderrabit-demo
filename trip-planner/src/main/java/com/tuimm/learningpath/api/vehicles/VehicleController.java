package com.tuimm.learningpath.api.vehicles;

import com.tuimm.learningpath.contracts.vehicles.CreateBikeRequest;
import com.tuimm.learningpath.contracts.vehicles.CreateVehicleResponse;
import com.tuimm.learningpath.contracts.vehicles.GetVehiclesResponse;
import com.tuimm.learningpath.domain.vehicles.Vehicle;
import com.tuimm.learningpath.domain.vehicles.VehiclesService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/vehicles")
@RequiredArgsConstructor
public class VehicleController {
    @NonNull
    private final VehiclesService vehiclesService;

    @GetMapping
    public ResponseEntity<GetVehiclesResponse> getVehicles() {
        return new ResponseEntity<>(new GetVehiclesResponse(vehiclesService.getAllVehicles()), HttpStatus.OK);
    }

    @PostMapping("/bikes")
    public ResponseEntity<CreateVehicleResponse> createBike(@RequestBody CreateBikeRequest request) {
        Vehicle vehicle = vehiclesService.addBike(request.getModel(),
                request.getMaxPeople(),
                request.getDailyRentPrice(),
                request.getAverageSpeed(),
                request.getAutonomy());
        return new ResponseEntity<>(new CreateVehicleResponse(vehicle.getId()), HttpStatus.OK);
    }




}
