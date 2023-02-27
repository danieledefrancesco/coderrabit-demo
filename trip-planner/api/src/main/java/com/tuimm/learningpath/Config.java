package com.tuimm.learningpath;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tuimm.learningpath.authorization.JWTAuthenticationFilter;
import com.tuimm.learningpath.authorization.Role;
import com.tuimm.learningpath.drivers.DriversDtoMapper;
import com.tuimm.learningpath.exceptions.ErrorResponseBuilder;
import com.tuimm.learningpath.trips.TripsDtoMapper;
import com.tuimm.learningpath.vehicles.FuelTypesDtoMapper;
import com.tuimm.learningpath.vehicles.VehiclesDtoMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.tuimm.learningpath.authorization.HierarchicalAuthorizationManager.*;

@Configuration
@ComponentScan("com.tuimm.learningpath")
@EnableMethodSecurity
@EnableWebSecurity
public class Config {
    @Bean
    public SecurityFilterChain web(HttpSecurity httpSecurity, JWTAuthenticationFilter filter) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.authorizeHttpRequests(authorize -> authorize
                .requestMatchers(HttpMethod.GET).permitAll()
                .requestMatchers(HttpMethod.POST).access(forRole(Role.OPERATOR))
                .requestMatchers(HttpMethod.PATCH, "/trips/{id}/plan/stages/{start}-to-{end}/driver").access(forRole(Role.MANAGER))
                .requestMatchers(HttpMethod.PATCH).access(forRole(Role.OPERATOR))
                .requestMatchers(HttpMethod.DELETE).access(forRole(Role.MANAGER))
                .anyRequest().denyAll());
        httpSecurity.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.exceptionHandling(exceptionHandling ->
                exceptionHandling.accessDeniedHandler(((request, response, accessDeniedException) -> {
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    response.setContentType("application/json");
                    response.getWriter().println(ErrorResponseBuilder.buildErrorBody(accessDeniedException, HttpStatus.FORBIDDEN.value()));
                })));
        return httpSecurity.build();
    }

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
