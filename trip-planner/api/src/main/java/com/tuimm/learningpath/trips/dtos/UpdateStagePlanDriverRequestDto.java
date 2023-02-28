package com.tuimm.learningpath.trips.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tuimm.learningpath.drivers.dtos.CreateDriverRequestDto;
import com.tuimm.learningpath.validators.AtLeastOneNotNull;
import com.tuimm.learningpath.validators.AtMostOneNotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AtLeastOneNotNull(fields = {"driverId", "driver"})
@AtMostOneNotNull(fields = {"driverId", "driver"})
public class UpdateStagePlanDriverRequestDto {
    @JsonProperty("driver_id")
    private UUID driverId;
    private CreateDriverRequestDto driver;
}
