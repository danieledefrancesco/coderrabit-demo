package com.tuimm.learningpath.infrastructure;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.util.Optional;

class HttpClientUtilsTest {
    private final String wiremockBaseUri = Optional.ofNullable(System.getenv("WIREMOCK_BASE_URI"))
            .orElse("http://localhost:8080");
    @Test
    void executeGet_shouldThrowServiceInvocationException_whenServiceReturnsAnHttpError()
    {
        HttpClient client = HttpClient.newBuilder().build();
        URI uri = URI.create(String.format("%s/404", wiremockBaseUri));
        Assertions.assertThrows(ServiceInvocationException.class,
                () -> HttpClientUtils.executeGet(client, uri, Object.class));
    }
    @Test
    void executeGet_shouldThrowServiceInvocationException_whenHttpClientThrowIOException()
    {
        HttpClient client = HttpClient.newBuilder().build();
        URI uri = URI.create("http://unexisting-service.abc.def");
        Assertions.assertThrows(ServiceInvocationException.class,
                () -> HttpClientUtils.executeGet(client, uri, Object.class));
    }
}
