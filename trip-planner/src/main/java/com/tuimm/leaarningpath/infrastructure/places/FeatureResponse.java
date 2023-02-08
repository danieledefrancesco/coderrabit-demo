package com.tuimm.leaarningpath.infrastructure.places;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FeatureResponse {
    private GeometryResponse geometry;
}
