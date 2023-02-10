package com.tuimm.learningpath;

import com.googlecode.cqengine.ConcurrentIndexedCollection;
import com.googlecode.cqengine.IndexedCollection;
import com.tuimm.learningpath.api.vehicles.VehiclesMapper;
import com.tuimm.learningpath.domain.vehicles.Vehicle;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.tuimm.learningpath")
public class Config {
    @Bean
    public IndexedCollection<Vehicle> indexedCollection() {
        return new ConcurrentIndexedCollection<>();
    }

    @Bean
    public VehiclesMapper vehiclesMapper() {
        return Mappers.getMapper(VehiclesMapper.class);
    }
}
