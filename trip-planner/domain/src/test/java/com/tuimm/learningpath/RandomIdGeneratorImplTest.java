package com.tuimm.learningpath;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RandomIdGeneratorImplTest {
    private RandomIdGeneratorImpl randomIdGenerator;

    @BeforeEach
    void setUp() {
        randomIdGenerator = new RandomIdGeneratorImpl();
    }

    @Test
    void generateRandomId_returnsARandomUUID() {
        Assertions.assertNotNull(randomIdGenerator.generateRandomId());
    }
}
