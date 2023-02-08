package com.tuimm.leaarningpath.common;

import java.util.UUID;

public class RandomIdGeneratorImpl implements RandomIdGenerator {
    @Override
    public UUID generateRandomId() {
        return UUID.randomUUID();
    }
}
