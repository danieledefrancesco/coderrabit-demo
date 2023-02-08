package com.tuimm.leaarningpath.infrastructure.routes;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FeatureResponse {
    private final PropertyResponse properties;
}
