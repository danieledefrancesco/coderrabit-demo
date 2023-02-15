package com.tuimm.learningpath.places;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "create")
@EqualsAndHashCode
public class Place {
    private final String name;
    private final GeoCoordinate geoCoordinate;
}
