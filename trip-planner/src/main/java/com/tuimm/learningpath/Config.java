package com.tuimm.learningpath;

import com.tuimm.learningpath.api.vehicles.VehiclesMapper;
import com.tuimm.learningpath.infrastructure.vehicles.VehicleEntitiesMapper;
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
    public HttpClient httpClient() {
        return HttpClient.newBuilder().build();
    }
}
