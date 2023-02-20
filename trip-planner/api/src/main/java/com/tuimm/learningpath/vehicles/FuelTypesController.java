package com.tuimm.learningpath.vehicles;

import com.tuimm.learningpath.mediator.Mediator;
import com.tuimm.learningpath.vehicles.dtos.GetAllFuelTypesResponseDto;
import com.tuimm.learningpath.vehicles.queries.GetAllFuelTypesRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
