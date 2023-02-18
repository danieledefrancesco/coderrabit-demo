package com.tuimm.learningpath.exceptions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EntityNotFoundExceptionTest {

    @Test
    void getMessage_shouldReturnExpectedMessage() {
        EntityNotFoundException exception = new EntityNotFoundException("EntityName", "EntityId");
        Assertions.assertEquals("EntityName with id EntityId does not exist.",
                exception.getMessage());
    }
}
