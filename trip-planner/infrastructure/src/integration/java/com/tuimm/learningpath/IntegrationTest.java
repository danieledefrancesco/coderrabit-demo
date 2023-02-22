package com.tuimm.learningpath;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ContextConfiguration(classes = InfrastructureConfig.class)
@SpringBootTest
public @interface IntegrationTest {

}
