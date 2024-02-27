package com.tuimm.learningpath.drivers.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class GetAvailableDriversResponseDto {
    private Collection<DriverResponseDto> drivers;
}
