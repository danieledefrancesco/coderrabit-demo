package com.tuimm.learningpath.trips;

import com.tuimm.learningpath.common.TimeSlot;
import com.tuimm.learningpath.mediator.Mediator;
import com.tuimm.learningpath.trips.commands.DeleteTripRequest;
import com.tuimm.learningpath.trips.commands.UpdateStageDriverRequest;
import com.tuimm.learningpath.trips.dtos.CreateTripRequestDto;
import com.tuimm.learningpath.trips.dtos.GetAllTripsResponseDto;
import com.tuimm.learningpath.trips.dtos.TripResponseDto;
import com.tuimm.learningpath.trips.dtos.UpdateStagePlanDriverRequestDto;
import com.tuimm.learningpath.trips.queries.GetAllTripsRequest;
import com.tuimm.learningpath.trips.queries.GetTripByIdRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/trips")
@RequiredArgsConstructor
public class TripsController {
    private final Mediator mediator;
    private final TripsDtoMapper mapper;

    @GetMapping
    public ResponseEntity<GetAllTripsResponseDto> get() {
        GetAllTripsResponseDto response = mapper.mapToGetAllTripsResponseDto(mediator.send(GetAllTripsRequest.create()));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TripResponseDto> getById(@PathVariable("id")UUID id) {
        TripResponseDto response = mapper.mapToTripResponseDto(mediator.send(GetTripByIdRequest.fromId(id)));
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Object> createTrip(@Valid @RequestBody CreateTripRequestDto request) {
        Trip trip = mediator.send(mapper.mapToCreateTripRequest(request));
        UriComponents uriComponentsBuilder = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(trip.getId());
        return ResponseEntity.created(uriComponentsBuilder.toUri()).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id")UUID id) {
        mediator.send(DeleteTripRequest.fromId(id));
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/plan/stages/{start}-to-{end}/driver")
    public ResponseEntity<Object> updateStageDriver(@PathVariable("id") UUID tripId,
                                                    @PathVariable("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                                                    @PathVariable("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end,
                                                    @Valid @RequestBody UpdateStagePlanDriverRequestDto updateStageDriverRequest) {
        UpdateStageDriverRequest request =  UpdateStageDriverRequest.builder()
                .tripId(tripId)
                .driverId(updateStageDriverRequest.getDriverId())
                .timeSlot(TimeSlot.builder()
                        .startDateTime(start)
                        .endDateTime(end)
                        .build())
                .build();
        mediator.send(request);
        return ResponseEntity.noContent().build();
    }
}
