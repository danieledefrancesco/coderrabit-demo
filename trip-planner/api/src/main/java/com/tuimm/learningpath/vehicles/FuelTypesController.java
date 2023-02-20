package com.tuimm.learningpath.vehicles;

import com.tuimm.learningpath.mediator.Mediator;
import com.tuimm.learningpath.vehicles.commands.UpdateFuelTypeCostRequest;
import com.tuimm.learningpath.vehicles.dtos.GetAllFuelTypesResponseDto;
import com.tuimm.learningpath.vehicles.dtos.UpdateFuelTypeCostRequestDto;
import com.tuimm.learningpath.vehicles.queries.GetAllFuelTypesRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/fuel-types")
@RequiredArgsConstructor
public class FuelTypesController {
    private final FuelTypesDtoMapper mapper;
    private final Mediator mediator;
    @GetMapping
    public ResponseEntity<GetAllFuelTypesResponseDto> getAllFuelTypes() {
        GetAllFuelTypesResponseDto response = mapper.mapToGetAllFuelTypesResponseDto(mediator.send(GetAllFuelTypesRequest.create()));
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{fuelType}/cost")
    public ResponseEntity<Object> updateFuelTypeCost(@PathVariable("fuelType") String fuelType, @Valid @RequestBody UpdateFuelTypeCostRequestDto updateFuelTypeCostRequestDto) {
        UpdateFuelTypeCostRequest request = UpdateFuelTypeCostRequest.create(fuelType, updateFuelTypeCostRequestDto.getCost());
        mediator.send(request);
        return ResponseEntity.noContent().build();
    }
}
