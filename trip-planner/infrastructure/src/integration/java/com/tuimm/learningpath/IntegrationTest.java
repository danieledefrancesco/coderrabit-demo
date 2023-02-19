package com.tuimm.learningpath;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootApplication
@ContextConfiguration(classes = InfrastructureConfig.class)
public abstract class IntegrationTest {

}
