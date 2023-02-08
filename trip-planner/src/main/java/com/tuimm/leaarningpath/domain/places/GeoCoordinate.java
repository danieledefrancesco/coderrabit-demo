package com.tuimm.leaarningpath.domain.places;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor(staticName = "of")
public class GeoCoordinate {
    private final double latitude;
    private final double longitude;
}
