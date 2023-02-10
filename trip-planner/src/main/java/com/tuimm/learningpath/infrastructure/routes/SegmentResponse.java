package com.tuimm.learningpath.infrastructure.routes;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SegmentResponse {
    private final double distance;
}
