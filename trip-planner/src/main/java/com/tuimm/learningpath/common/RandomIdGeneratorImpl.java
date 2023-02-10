package com.tuimm.learningpath.common;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RandomIdGeneratorImpl implements RandomIdGenerator {
    @Override
    public UUID generateRandomId() {
        return UUID.randomUUID();
    }
}
