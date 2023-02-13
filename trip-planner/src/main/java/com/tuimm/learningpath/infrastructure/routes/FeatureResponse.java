package com.tuimm.learningpath.infrastructure.routes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeatureResponse {
    private PropertyResponse properties;
}
