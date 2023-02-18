package com.tuimm.learningpath.mediator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class RequestHandlerTest {
    private static class TestRequest implements Request<Object> { }
    private static abstract class TestRequestHandler extends RequestHandler<TestRequest, Object> {
        public TestRequestHandler() {
            super(TestRequest.class);
        }
    }
    private TestRequest request;
    private Object response;
    private TestRequestHandler handler;

    @BeforeEach
    void setUp() {
        request = new TestRequest();
        response = mock(Object.class);
        handler = spy(new TestRequestHandler() {

            @Override
            public Object handle(TestRequest request) {
                return null;
            }
        });
    }

    @Test
    void getRequestType_shouldReturnExpectedRequest() {
        Assertions.assertEquals(TestRequest.class, handler.getRequestType());
    }

    @Test
    void handleInternal_shouldReturnExpectedResponse_whenRequestIsOfCorrectType() {
        when(handler.handle(request)).thenReturn(response);
        clearInvocations(handler);
        Assertions.assertEquals(response,
                handler.handleInternal(request));
        verify(handler, times(1)).handle(request);
    }

    @Test
    void handleInternal_shouldThrowUnsupportedOperationException_whenRequestIsOfWrongType() {
        Assertions.assertThrows(UnsupportedOperationException.class,
                () -> handler.handleInternal(mock(Request.class)));
    }
}
