package com.tuimm.learningpath.drivers;

import com.tuimm.learningpath.drivers.commands.DeleteDriverRequest;
import com.tuimm.learningpath.drivers.dtos.CreateDriverRequestDto;
import com.tuimm.learningpath.drivers.dtos.DriverDto;
import com.tuimm.learningpath.drivers.dtos.GetAllDriversResponseDto;
import com.tuimm.learningpath.drivers.queries.GetAllDriversRequest;
import com.tuimm.learningpath.drivers.queries.GetDriverByIdRequest;
import com.tuimm.learningpath.mediator.Mediator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import java.util.UUID;

@Controller
@RequestMapping(value = "/drivers")
@RequiredArgsConstructor
public class DriversController {
    private final Mediator mediator;
    private final DriversDtoMapper mapper;

    @GetMapping
    public ResponseEntity<GetAllDriversResponseDto> getAll() {
        GetAllDriversResponseDto response = mapper.map(mediator.send(new GetAllDriversRequest()));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverDto> getSingle(@PathVariable("id")UUID id) {
        DriverDto response = mapper.map(mediator.send(GetDriverByIdRequest.fromId(id)));
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id")UUID id) {
        mediator.send(DeleteDriverRequest.fromId(id));
        return ResponseEntity.noContent().build();
    }
}