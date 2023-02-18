package com.tuimm.learningpath.exceptions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EntityAlreadyExistsExceptionTest {

    @Test
    void getMessage_shouldReturnExpectedMessage() {
        EntityAlreadyExistsException exception = new EntityAlreadyExistsException("EntityName", "EntityId");
        Assertions.assertEquals("EntityName with id EntityId already exists.",
                exception.getMessage());
    }
}
