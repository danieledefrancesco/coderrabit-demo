package com.tuimm.learningpath.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientUtils {
    private HttpClientUtils() {

    }
    public static <T> T executeGet(HttpClient httpClient, URI uri, Class<T> outputClass) {
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(uri)
                .GET()
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() > 399) {
                throw new ServiceInvocationException(
                        String.format(
                                "error while invoking %s, status code was %d.",
                                uri,
                                response.statusCode()));
            }
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(response.body(), outputClass);
        } catch (IOException | InterruptedException e) {
            throw new ServiceInvocationException(e);
        }
    }
}
