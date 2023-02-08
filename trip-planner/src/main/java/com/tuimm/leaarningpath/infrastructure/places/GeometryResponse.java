package com.tuimm.leaarningpath.infrastructure.places;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class GeometryResponse {
    private List<Double> coordinates;
}
