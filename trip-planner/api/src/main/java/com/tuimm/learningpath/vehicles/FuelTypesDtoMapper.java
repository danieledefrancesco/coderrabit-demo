package com.tuimm.learningpath.vehicles;

import com.tuimm.learningpath.vehicles.dtos.FuelTypeResponseDto;
import com.tuimm.learningpath.vehicles.dtos.GetAllFuelTypesResponseDto;
import com.tuimm.learningpath.vehicles.queries.GetAllFuelTypesResponse;
import org.mapstruct.Mapper;

@Mapper
public interface FuelTypesDtoMapper {
    GetAllFuelTypesResponseDto mapToGetAllFuelTypesResponseDto(GetAllFuelTypesResponse response);
    FuelTypeResponseDto mapToFuelTypeResponseDto(FuelType fuelType);
}
