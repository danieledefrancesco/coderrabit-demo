package com.tuimm.learningpath.drivers;

import com.tuimm.learningpath.drivers.commands.DeleteDriverRequest;
import com.tuimm.learningpath.drivers.dtos.CreateDriverRequestDto;
import com.tuimm.learningpath.drivers.dtos.DriverResponseDto;
import com.tuimm.learningpath.drivers.dtos.GetAllDriversResponseDto;
import com.tuimm.learningpath.drivers.dtos.UpdateDriverRequestDto;
import com.tuimm.learningpath.drivers.queries.GetAllDriversRequest;
import com.tuimm.learningpath.drivers.queries.GetDriverByIdRequest;
import com.tuimm.learningpath.mediator.Mediator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import java.util.UUID;

@RestController
@RequestMapping(value = "/drivers")
@RequiredArgsConstructor
public class DriversController {
    private final Mediator mediator;
    private final DriversDtoMapper mapper;

    @GetMapping
    public ResponseEntity<GetAllDriversResponseDto> getAll() {
        GetAllDriversResponseDto response = mapper.map(mediator.send(GetAllDriversRequest.create()));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverResponseDto> getSingle(@PathVariable("id")UUID id) {
        DriverResponseDto response = mapper.map(mediator.send(GetDriverByIdRequest.fromId(id)));
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody CreateDriverRequestDto createDriverRequest) {
        Driver driver = mediator.send(mapper.map(createDriverRequest));
        UriComponents uriComponentsBuilder = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(driver.getId());
        return ResponseEntity.created(uriComponentsBuilder.toUri()).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id")UUID id,
                                        @Valid @RequestBody UpdateDriverRequestDto updateDriverRequestDto) {
        mediator.send(mapper.map(updateDriverRequestDto, id));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id")UUID id) {
        mediator.send(DeleteDriverRequest.fromId(id));
        return ResponseEntity.noContent().build();
    }
}
