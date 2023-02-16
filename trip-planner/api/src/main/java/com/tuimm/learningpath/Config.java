package com.tuimm.learningpath;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tuimm.learningpath.drivers.DriversDtoMapper;
import com.tuimm.learningpath.vehicles.VehiclesMapper;
import com.tuimm.learningpath.drivers.DriverEntityMapper;
import com.tuimm.learningpath.vehicles.VehicleEntitiesMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;

@Configuration
@ComponentScan("com.tuimm.learningpath")
public class Config {

    @Bean
    public VehiclesMapper vehiclesMapper() {
        return Mappers.getMapper(VehiclesMapper.class);
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
    public HttpClient httpClient() {
        return HttpClient.newBuilder().build();
    }

    @Bean
    public com.fasterxml.jackson.databind.Module javaTimeModule() {
        return new JavaTimeModule();
    }
}
