package com.tuimm.leaarningpath.infrastructure.routes;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SegmentResponse {
    private final double distance;
}
