package com.tuimm.learningpath.mediator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;

import static org.mockito.Mockito.*;

class MediatorImplTest {
    private static class TestRequest1 implements Request<Object> { }
    private static class TestRequest2 implements Request<Object> { }

    private TestRequest1 request1;
    private TestRequest2 request2;
    private Object response;
    private RequestHandler<?,?> handler;
    private MediatorImpl mediator;

    @BeforeEach
    void setUp() {
        request1 = new TestRequest1();
        request2 = new TestRequest2();
        response = mock(Object.class);
        handler = mock(RequestHandler.class);
        when(handler.getRequestType()).thenReturn((Class)TestRequest1.class);
        mediator = new MediatorImpl(Collections.singleton(handler), Collections.emptyList());
    }

    @Test
    void send_shouldReturnExpectedResponse_whenAnHandlerForTheRequestExists() {
        when(handler.handleInternal(request1)).thenReturn(response);
        Assertions.assertEquals(response, mediator.send(request1));
        verify(handler, times(1)).getRequestType();
        verify(handler, times(1)).handleInternal(request1);
    }

    @Test
    void send_shouldThrowUnsupportedOperationException_whenAnHandlerForTheRequestDoesNotExists() {
        Assertions.assertThrows(UnsupportedOperationException.class,
                () -> mediator.send(request2),
                "No handler found for request of type TestRequest2.");
        verify(handler, times(1)).getRequestType();
    }
}
