package com.tuimm.leaarningpath.infrastructure.routes;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@JsonDeserialize(using = DirectionsResponseDeserializer.class)
public class DirectionsResponse {
    private final List<FeatureResponse> features;
}
