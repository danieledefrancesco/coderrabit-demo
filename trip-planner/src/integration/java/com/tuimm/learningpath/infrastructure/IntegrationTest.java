package com.tuimm.learningpath.infrastructure;

import com.tuimm.learningpath.SpringChallenge;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringChallenge.class)
@TestPropertySource(locations = "classpath:application.properties")
public abstract class IntegrationTest {
}
