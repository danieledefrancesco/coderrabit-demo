package com.tuimm.learningpath;


import com.tuimm.learningpath.drivers.DriverEntityMapper;
import com.tuimm.learningpath.trips.TripEntityMapper;
import com.tuimm.learningpath.vehicles.VehicleEntitiesMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.net.http.HttpClient;

@Configuration
@ComponentScan("com.tuimm.learningpath")
@PropertySource(value="classpath:infrastructure.properties")
public class InfrastructureConfig {
    @Bean
    public VehicleEntitiesMapper vehicleEntitiesMapper() {
        return Mappers.getMapper(VehicleEntitiesMapper.class);
    }

    @Bean
    public DriverEntityMapper driverEntityMapper() {
        return Mappers.getMapper(DriverEntityMapper.class);
    }

    @Bean
    public TripEntityMapper tripEntityMapper() { return Mappers.getMapper(TripEntityMapper.class); }

    @Bean
    public HttpClient httpClient() {
        return HttpClient.newBuilder().build();
    }
}
