package com.tuimm.learningpath.places;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeatureResponse {
    private GeometryResponse geometry;
}
