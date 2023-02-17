package com.tuimm.learningpath.trips.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class GetAllTripsResponseDto {
    private Collection<TripResponseDto> trips;
}
