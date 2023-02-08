package com.tuimm.leaarningpath.infrastructure.routes;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PropertyResponse {
    private final List<SegmentResponse> segments;
}
