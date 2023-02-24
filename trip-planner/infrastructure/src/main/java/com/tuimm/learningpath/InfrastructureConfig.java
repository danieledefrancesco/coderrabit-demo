package com.tuimm.learningpath;

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
    public HttpClient httpClient() {
        return HttpClient.newBuilder().build();
    }
}
