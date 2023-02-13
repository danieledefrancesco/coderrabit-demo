package com.tuimm.learningpath.infrastructure.routes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DirectionsResponse {
    private List<FeatureResponse> features;
}
