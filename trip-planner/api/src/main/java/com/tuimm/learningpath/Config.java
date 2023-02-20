package com.tuimm.learningpath;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tuimm.learningpath.drivers.DriversDtoMapper;
import com.tuimm.learningpath.trips.TripsDtoMapper;
import com.tuimm.learningpath.vehicles.FuelTypesDtoMapper;
import com.tuimm.learningpath.vehicles.VehiclesDtoMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.tuimm.learningpath")
public class Config {

    @Bean
    public VehiclesDtoMapper vehiclesMapper() {
        return Mappers.getMapper(VehiclesDtoMapper.class);
    }
    @Bean
    public FuelTypesDtoMapper fuelTypesDtoMapper() { return Mappers.getMapper(FuelTypesDtoMapper.class); }
    @Bean
    public DriversDtoMapper driversDtoMapper() {
        return Mappers.getMapper(DriversDtoMapper.class);
    }
    @Bean
    public TripsDtoMapper tripsDtoMapper() { return Mappers.getMapper(TripsDtoMapper.class); }
    @Bean
    public com.fasterxml.jackson.databind.Module javaTimeModule() {
        return new JavaTimeModule();
    }
}
