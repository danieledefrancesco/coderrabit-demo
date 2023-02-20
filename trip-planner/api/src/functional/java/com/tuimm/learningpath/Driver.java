package com.tuimm.learningpath;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Getter
@RequiredArgsConstructor
public class Driver {
    private final int port;
    private final HttpClient httpClient = HttpClient.newBuilder().build();
    private HttpResponse<String> lastResponse;
    @Setter
    private Object requestBody;
    private final ObjectMapper objectMapper = createObjectMapper();
    private void executeRequest(HttpRequest request) {
        try {
            lastResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new UnsupportedOperationException(e);
        }
    }
    public void executeGet(String path) {
        executeRequest(HttpRequest.newBuilder()
                .GET()
                .uri(createUri(path))
                .build());
    }

    public void executePost(String path) {
        executeRequest(HttpRequest.newBuilder()
                .POST(getRequestBodyAsJsonString())
                .header("Content-Type", "application/json")
                .uri(createUri(path))
                .build());
    }

    public void executePatch(String path) {
        executeRequest(HttpRequest.newBuilder()
                .method("PATCH", getRequestBodyAsJsonString())
                .header("Content-Type", "application/json")
                .uri(createUri(path))
                .build());
    }

    public void executeDelete(String path) {
        executeRequest(HttpRequest.newBuilder()
                .DELETE()
                .uri(createUri(path))
                .build());
    }

    private URI createUri(String path) {
        return URI.create(String.format("%s%s", getBaseUri(), path));
    }
    public String getBaseUri() {
        return "http://localhost:"+port;
    }

    private HttpRequest.BodyPublisher getRequestBodyAsJsonString() {
        try {
            return HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(requestBody));
        } catch (JsonProcessingException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    public <T> T getLastResponseAs(Class<T> type) {
        try {
            return objectMapper.readValue(lastResponse.body(), type);
        } catch (JsonProcessingException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }
}
