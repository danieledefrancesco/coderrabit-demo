package com.tuimm.leaarningpath.infrastructure.places;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@JsonDeserialize(using = SearchResponseDeserializer.class)
public class SearchResponse {
    private List<FeatureResponse> features;
}
