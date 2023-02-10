package com.tuimm.learningpath.infrastructure.routes;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.List;
import java.util.stream.StreamSupport;

public class DirectionsResponseDeserializer extends StdDeserializer<DirectionsResponse> {
    public DirectionsResponseDeserializer() {
        super(DirectionsResponse.class);
    }

    @Override
    public DirectionsResponse deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode treeNode = jsonParser.readValueAsTree();

        List<FeatureResponse> features = StreamSupport.stream(treeNode.get("features").spliterator(), false)
                .map(DirectionsResponseDeserializer::mapFeatureResponse)
                .toList();

        return DirectionsResponse.builder()
                .features(features)
                .build();
    }

    private static FeatureResponse mapFeatureResponse(JsonNode jsonNode) {
        return FeatureResponse.builder()
                .properties(mapPropertyResponse(jsonNode.get("properties")))
                .build();
    }

    private static PropertyResponse mapPropertyResponse(JsonNode jsonNode) {
        List<SegmentResponse> segments = StreamSupport.stream(jsonNode.get("segments").spliterator(), false)
                .map(DirectionsResponseDeserializer::mapSegmentResponse)
                .toList();

        return PropertyResponse.builder()
                .segments(segments)
                .build();
    }

    private static SegmentResponse mapSegmentResponse(JsonNode jsonNode) {
        return SegmentResponse.builder()
                .distance(jsonNode.get("distance").asDouble())
                .build();
    }
}
