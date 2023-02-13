package com.tuimm.learningpath.infrastructure.places;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResponse {
    private List<FeatureResponse> features;
}
