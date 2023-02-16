package com.tuimm.learningpath.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class ErrorResponseBuilder {
    private ErrorResponseBuilder() {

    }

    public static String buildErrorBody(Exception exception, int statusCode) {
        return String.format("""
                        {
                            "timestamp": "%s",
                            "status": %d,
                            "error": "%s",
                            "path": "%s"
                        }""",
                OffsetDateTime.now().atZoneSameInstant(ZoneOffset.UTC),
                statusCode,
                exception.getMessage(),
                ServletUriComponentsBuilder.fromCurrentRequest().build().toUri());
    }

    public static HttpHeaders buildHttpHeaders() {
        HttpHeaders result = new HttpHeaders();
        result.add("content-type", "application/json");
        return result;
    }
}
