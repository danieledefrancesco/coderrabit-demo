package com.tuimm.learningpath.infrastructure.places;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.List;
import java.util.stream.StreamSupport;

public class SearchResponseDeserializer extends StdDeserializer<SearchResponse> {
    protected SearchResponseDeserializer() {
        super(SearchResponse.class);
    }

    @Override
    public SearchResponse deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode treeNode = jsonParser.readValueAsTree();
        List<FeatureResponse> features = StreamSupport.stream(treeNode.get("features").spliterator(), false)
                .map(SearchResponseDeserializer::mapFeatureResponse)
                .toList();

        return SearchResponse.builder()
                .features(features)
                .build();
    }

    private static FeatureResponse mapFeatureResponse(JsonNode jsonNode) {
        return FeatureResponse.builder()
                .geometry(mapGeometryResponse(jsonNode.get("geometry")))
                .build();
    }

    private static GeometryResponse mapGeometryResponse(JsonNode jsonNode) {
        List<Double> coordinates = StreamSupport.stream(jsonNode.get("coordinates").spliterator(), false)
                .map(JsonNode::asDouble)
                .toList();

        return GeometryResponse.builder()
                .coordinates(coordinates)
                .build();
    }
}
