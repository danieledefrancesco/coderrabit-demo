package com.tuimm.learningpath.infrastructure.routes;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FeatureResponse {
    private final PropertyResponse properties;
}
