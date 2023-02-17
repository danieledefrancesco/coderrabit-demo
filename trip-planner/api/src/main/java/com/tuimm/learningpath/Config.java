package com.tuimm.learningpath;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tuimm.learningpath.drivers.DriversDtoMapper;
import com.tuimm.learningpath.trips.StagePlan;
import com.tuimm.learningpath.trips.TripEntityMapper;
import com.tuimm.learningpath.trips.TripsDtoMapper;
import com.tuimm.learningpath.vehicles.VehiclesDtoMapper;
import com.tuimm.learningpath.drivers.DriverEntityMapper;
import com.tuimm.learningpath.vehicles.VehicleEntitiesMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;
import java.util.function.Supplier;

@Configuration
@ComponentScan("com.tuimm.learningpath")
public class Config {

    @Bean
    public VehiclesDtoMapper vehiclesMapper() {
        return Mappers.getMapper(VehiclesDtoMapper.class);
    }

    @Bean
    public VehicleEntitiesMapper vehicleEntitiesMapper() {
        return Mappers.getMapper(VehicleEntitiesMapper.class);
    }

    @Bean
    public DriverEntityMapper driverEntityMapper() {
        return Mappers.getMapper(DriverEntityMapper.class);
    }

    @Bean
    public DriversDtoMapper driversDtoMapper() {
        return Mappers.getMapper(DriversDtoMapper.class);
    }

    @Bean
    public TripEntityMapper tripEntityMapper() { return Mappers.getMapper(TripEntityMapper.class); }

    @Bean
    public TripsDtoMapper tripsDtoMapper() { return Mappers.getMapper(TripsDtoMapper.class); }

    @Bean
    public HttpClient httpClient() {
        return HttpClient.newBuilder().build();
    }

    @Bean
    public com.fasterxml.jackson.databind.Module javaTimeModule() {
        return new JavaTimeModule();
    }

    @Bean
    public Supplier<StagePlan.StagePlanBuilder> stagePlanBuilderSupplier() {
        return StagePlan::builder;
    }
}
